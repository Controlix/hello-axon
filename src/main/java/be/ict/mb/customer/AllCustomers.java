package be.ict.mb.customer;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AllCustomers {

    private final List<Customer> customers = new ArrayList<>();

    @EventHandler
    public void on(NewCustomerEvent event) {
        customers.add(new Customer(event.getId(), event.getName()));
    }

    @EventHandler
    public void on(CustomerInterestedInNewProductsEvent event) {
        findCustomerById(event.getId())
                .ifPresent(c -> c.addInterestedCategory(event.getCategory()));
    }

    private Optional<Customer> findCustomerById(String id) {
        return customers.stream().filter(c -> id.equals(c.getId()))
                .findFirst();
    }

    @EventHandler
    public void on(CustomerNoLongerInterestedInProductsEvent event) {
        findCustomerById(event.getId())
                .ifPresent(c -> c.removeInterestedCategory(event.getCategory()));
    }

    @EventHandler
    public void on(NewProductAvailableEvent event) {
        log.info("Hey {}, we have a new product you might be interested in: {}",
                event.getCustomerId(), event.getProductId());
    }

    @QueryHandler
    public List<Customer> handle(FindAllCustomersQuery query) {
        return customers;
    }
}
