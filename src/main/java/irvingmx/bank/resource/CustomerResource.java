package irvingmx.bank.resource;

import irvingmx.bank.domain.Customer;
import irvingmx.bank.exception.CustomerRegisteredException;
import irvingmx.bank.model.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerResource {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @PostMapping("/customers")
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customer.getDocument());
        if(optionalCustomer.isPresent()){
            throw new CustomerRegisteredException("There is already a customer registered with this document!!! " + customer.getDocument());
        }
        Customer savedCustomer = customerRepository.save(customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{document}")
                .buildAndExpand(savedCustomer.getDocument()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/customers/{document}")
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer, @PathVariable String document) {
        Optional<Customer> optionalCustomer = customerRepository.findById(document);
        if(!optionalCustomer.isPresent()){
            return ResponseEntity.notFound().build();
        }
        customer.setDocument(document);
        customerRepository.save(customer);
        return ResponseEntity.noContent().build();
    }
}
