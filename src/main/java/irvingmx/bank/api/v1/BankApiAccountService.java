package irvingmx.bank.api.v1;

import irvingmx.bank.domain.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/v1")
public interface BankApiAccountService {
    @GetMapping("/accounts")
    List<Account> getAllAccounts();

    @PostMapping("/accounts")
    ResponseEntity<Object> createAccount(@RequestBody Account account);

    @PutMapping("/accounts/{accountNumber}")
    ResponseEntity<Object> updateAccountBalance(@RequestBody Account account, @PathVariable String accountNumber);
}
