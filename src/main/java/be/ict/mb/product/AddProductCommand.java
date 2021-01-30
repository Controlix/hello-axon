package be.ict.mb.product;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class AddProductCommand {
    @TargetAggregateIdentifier String id;
    String name;
}
