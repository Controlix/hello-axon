package be.ict.mb.customer;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CreateCustomerCommand {

    @TargetAggregateIdentifier String id;
    String name;
}
