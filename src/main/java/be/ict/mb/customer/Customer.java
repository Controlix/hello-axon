package be.ict.mb.customer;

import lombok.Value;

import java.util.Set;
import java.util.TreeSet;

@Value
public class Customer {

    String id;
    String name;
    Set<String> interestedCategories = new TreeSet<>();

    public void addInterestedCategory(String category) {
        this.interestedCategories.add(category);
    }

    public void removeInterestedCategory(String category) {
        this.interestedCategories.remove(category);
    }
}
