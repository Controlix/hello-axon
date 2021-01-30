package be.ict.mb.product;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@Aggregate
public class ProductAggregate {

    @AggregateIdentifier private String id;
    private String name;
    private String category;

    @CommandHandler
    public ProductAggregate(AddProductCommand command) {
        log.debug("Got command {}", command);
        AggregateLifecycle.apply(new NewProductEvent(command.getId(), command.getName(), command.getCategory()));
    }

    @EventSourcingHandler
    public void on(NewProductEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.category = event.getCategory();
    }
}
