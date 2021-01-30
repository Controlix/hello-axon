package be.ict.mb.product;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier private String id;
    private String name;

    @CommandHandler
    public ProductAggregate(AddProductCommand command) {

    }

    @EventSourcingHandler
    public void on(NewProductEvent event) {
        this.id = event.getId();
        this.name = event.getName();
    }
}
