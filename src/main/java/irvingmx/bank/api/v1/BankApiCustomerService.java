package irvingmx.bank.api.v1;

import irvingmx.bank.domain.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/v1")
public interface BankApiCustomerService {
    @GetMapping("/customers")
    List<Customer> getAllCustomers();

    @PostMapping("/customers")
    ResponseEntity<Object> createCustomer(@RequestBody Customer customer);

    @PutMapping("/customers/{document}")
    ResponseEntity<Object> updateCustomer(@RequestBody Customer customer, @PathVariable String document);
}
