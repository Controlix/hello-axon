package be.ict.mb.product;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AllProducts {

    private final List<Product> products = new ArrayList<>();

    @EventHandler
    public void on(NewProductEvent event) {
        products.add(new Product(event.getId(), event.getName()));
    }

    @QueryHandler
    public List<Product> handle(FindAllProductsQuery query) {
        return products;
    }

    @QueryHandler
    public Product handle(FindOneProductQuery query) {
        return products.stream().filter(p -> query.getId().equals(p.getId())).findFirst().get();
    }
}
