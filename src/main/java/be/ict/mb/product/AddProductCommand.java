package be.ict.mb.product;

import lombok.Value;

@Value
public class AddProductCommand {
    String id;
    String name;
}
