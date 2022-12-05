package irvingmx.bank.resource;

import irvingmx.bank.domain.Customer;
import irvingmx.bank.domain.Transaction;
import irvingmx.bank.model.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class TransactionResource implements irvingmx.bank.api.v1.BankApiTransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    @GetMapping("/transactions/{document}")
    public List<Transaction> getAllTransactionsByCustomer(@PathVariable String document) {
        Customer customer = new Customer();
        customer.setDocument(document);
        return transactionRepository.findByCustomer(customer);
    }
}
