package irvingmx.bank.api.v1;

import irvingmx.bank.domain.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/v1")
public interface BankApiTransactionService {
    @GetMapping("/transactions")
    List<Transaction> getAllTransactions();

    @GetMapping("/transactions/{document}")
    List<Transaction> getAllTransactionsByCustomer(@PathVariable String document);
}
