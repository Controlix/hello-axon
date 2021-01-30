package be.ict.mb.product;

import lombok.Value;

@Value
public class NewProductEvent {
    String id;
    String name;
    String category;
}
