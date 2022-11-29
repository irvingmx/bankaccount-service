package irvingmx.bank.resource;

import irvingmx.bank.domain.Customer;
import irvingmx.bank.domain.Transaction;
import irvingmx.bank.model.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionResource {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @GetMapping("/transactions/{document}")
    public List<Transaction> getAllTransactionsByCustomer(@PathVariable String document) {
        Customer customer = new Customer();
        customer.setDocument(document);
        return transactionRepository.findByCustomer(customer);
    }
}
