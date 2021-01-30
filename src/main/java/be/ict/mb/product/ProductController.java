package be.ict.mb.product;

import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody AddProductCommand command) {
        commandGateway.sendAndWait(command, 2, TimeUnit.SECONDS);
        return ResponseEntity.ok(command.getId());
    }

    @GetMapping
    public List<Product> allProducts() {
        return queryGateway.query(new FindAllProductsQuery(),
                ResponseTypes.multipleInstancesOf(Product.class)).join();
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getOne(@PathVariable("id") String id) {
        return ResponseEntity.of(
                queryGateway.query(new FindOneProductQuery(id),
                        ResponseTypes.optionalInstanceOf(Product.class)).join());
    }
}
