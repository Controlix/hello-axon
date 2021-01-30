package be.ict.mb.customer;

import lombok.Value;

@Value
public class NewCustomerEvent {
    String id;
    String name;
}
