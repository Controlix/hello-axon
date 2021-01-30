package be.ict.mb.customer;

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
@RequestMapping("customer")
public class CustomerController {

    final CommandGateway commandGateway;
    final QueryGateway queryGateway;

    @PostMapping
    public ResponseEntity<String> newCustomer(@RequestBody CreateCustomerCommand command) {
        commandGateway.sendAndWait(command, 2, TimeUnit.SECONDS);
        return ResponseEntity.ok(command.getId());
    }

    @GetMapping
    public ResponseEntity<List<Customer>> allCustomers() {
        return ResponseEntity.ok(queryGateway.query(new FindAllCustomersQuery(),
                ResponseTypes.multipleInstancesOf(Customer.class)).join());
    }
}
