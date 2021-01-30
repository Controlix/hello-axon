package be.ict.mb.customer;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class NotifyCustomerOfNewProduct {

    @TargetAggregateIdentifier String customerId;
    String productId;
    String category;
}
