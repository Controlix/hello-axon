package be.ict.mb.customer;

import lombok.Value;

@Value
public class CustomerNoLongerInterestedInProductsEvent {
    String id;
    String category;
}
