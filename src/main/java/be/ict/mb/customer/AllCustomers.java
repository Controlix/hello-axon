package be.ict.mb.customer;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @QueryHandler
    public List<Customer> handle(FindAllCustomersQuery query) {
        return customers;
    }
}
