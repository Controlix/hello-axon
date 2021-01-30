package be.ict.mb.customer;

import be.ict.mb.product.NewProductEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Saga
public class CustomerNotificationSaga {

    private Set<String> customersIds = new HashSet<>();

    @StartSaga
    @SagaEventHandler(associationProperty = "category")
    public void handle(CustomerInterestedInNewProductsEvent event) {
        log.info("Customer {} is interested in new products of category {}", event.getId(), event.getCategory());
        this.customersIds.add(event.getId());
    }

    @SagaEventHandler(associationProperty = "category")
    public void handle(NewProductEvent event) {
        customersIds.forEach(c ->
                log.info("Hey customer {}, here is a new product you might be interested in {}",
                        c, event.getName()));
    }

    @SagaEventHandler(associationProperty = "category")
    public void handle(CustomerNoLongerInterestedInProductsEvent event) {
        log.info("Customer {} is no longer interested in new products of category {}",
                event.getId(), event.getCategory());
        this.customersIds.remove(event.getId());

        if (customersIds.isEmpty()) SagaLifecycle.end();
    }
}
