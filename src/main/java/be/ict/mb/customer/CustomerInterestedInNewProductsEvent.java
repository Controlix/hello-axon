package be.ict.mb.customer;

import lombok.Value;

@Value
public class CustomerInterestedInNewProductsEvent {

    String id;
    String category;
}
