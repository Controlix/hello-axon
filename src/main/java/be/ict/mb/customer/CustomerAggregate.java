package be.ict.mb.customer;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class CustomerAggregate {

    @AggregateIdentifier private String id;
    private String name;

    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand command) {
        AggregateLifecycle.apply(new NewCustomerEvent(command.getId(), command.getName()));
    }

    @EventSourcingHandler
    public void on(NewCustomerEvent event) {
        this.id = event.getId();
        this.name = event.getName();
    }

}
