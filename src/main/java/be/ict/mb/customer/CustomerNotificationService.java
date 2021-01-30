package be.ict.mb.customer;

import be.ict.mb.product.NewProductEvent;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerNotificationService {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @EventHandler
    public void handle(NewProductEvent event) {
        queryGateway.query(new FindAllCustomersQuery(),
                ResponseTypes.multipleInstancesOf(Customer.class))
                .thenAccept(customers -> customers.forEach(c -> notifyOfNewProduct(c, event)))
                .join();
    }

    private void notifyOfNewProduct(Customer customer, NewProductEvent event) {
        commandGateway.send(new NotifyCustomerOfNewProduct(customer.getId(), event.getId(), event.getCategory()));
    }
}
