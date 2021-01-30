package be.ict.mb.product;

import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {

    private CommandGateway commandGateway;

  //  @PostMapping
    public ResponseEntity<String> addProduct(AddProductCommand command) {
        commandGateway.sendAndWait(command, 2, TimeUnit.SECONDS);
        return ResponseEntity.ok(command.getId());
    }

    @GetMapping
    public ResponseEntity<String> allProducts() {
        return ResponseEntity.ok("no products yet");
    }
}
