package irvingmx.bank.resource;

import irvingmx.bank.domain.Account;
import irvingmx.bank.exception.AccountRegisteredException;
import irvingmx.bank.model.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountResource {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @PostMapping("/accounts")
    public ResponseEntity<Object> createAccount(@RequestBody Account account) {
        Optional<Account> optionalAccount = accountRepository.findById(account.getAccountNumber());
        if(optionalAccount.isPresent()){
            throw new AccountRegisteredException("There is already an account registered with this account number!!! " + account.getAccountNumber());
        }
        Account savedAccount = accountRepository.save(account);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{accountNumber}")
                .buildAndExpand(savedAccount.getAccountNumber()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/accounts/{accountNumber}")
    public ResponseEntity<Object> updateAccountBalance(@PathVariable Account account, @PathVariable String accountNumber) {
        Optional<Account> accountOptional = accountRepository.findById(accountNumber);
        if(!accountOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Account storedAccount = accountOptional.get();
        double currentBalance = storedAccount.getBalance();
        storedAccount.setBalance(currentBalance + account.getBalance());
        accountRepository.save(storedAccount);
        return ResponseEntity.noContent().build();
    }

}
