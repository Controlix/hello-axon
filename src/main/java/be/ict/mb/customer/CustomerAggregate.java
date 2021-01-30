package be.ict.mb.customer;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Set;
import java.util.TreeSet;

@NoArgsConstructor
@Aggregate
public class CustomerAggregate {

    @AggregateIdentifier private String id;
    private String name;
    private final Set<String> interestedCategories = new TreeSet<>();

    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand command) {
        AggregateLifecycle.apply(new NewCustomerEvent(command.getId(), command.getName()));
    }

    @CommandHandler
    public void handle(NotifyCustomerOfNewProductsCommand command) {
        if (this.interestedCategories.stream().anyMatch(cat -> command.getCategory().equals(cat))) return;
        AggregateLifecycle.apply(new CustomerInterestedInNewProductsEvent(command.getId(), command.getCategory()));
    }

    @EventSourcingHandler
    public void on(NewCustomerEvent event) {
        this.id = event.getId();
        this.name = event.getName();
    }

    @EventSourcingHandler
    public void on(CustomerInterestedInNewProductsEvent event) {
        interestedCategories.add(event.getCategory());
    }
}
