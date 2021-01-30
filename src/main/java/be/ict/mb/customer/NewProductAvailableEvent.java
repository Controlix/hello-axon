package be.ict.mb.customer;

import lombok.Value;

@Value
public class NewProductAvailableEvent {

    String customerId;
    String productId;
    String category;
}
