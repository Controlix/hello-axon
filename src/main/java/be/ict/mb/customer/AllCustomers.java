package be.ict.mb.customer;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AllCustomers {

    private final List<Customer> customers = new ArrayList<>();

    @EventHandler
    public void on(NewCustomerEvent event) {
        customers.add(new Customer(event.getId(), event.getName()));
    }

    @EventHandler
    public void on(CustomerInterestedInNewProductsEvent event) {
        customers.stream().filter(c -> event.getId().equals(c.getId()))
                .findFirst()
                .ifPresent(c -> c.addInterestedCategory(event.getCategory()));
    }

    @QueryHandler
    public List<Customer> handle(FindAllCustomersQuery query) {
        return customers;
    }
}
