package irvingmx.bank.resource;

import irvingmx.bank.domain.Account;
import irvingmx.bank.enums.TransactionType;
import irvingmx.bank.exception.AccountRegisteredException;
import irvingmx.bank.model.AccountRepository;
import irvingmx.bank.service.NotificationService;
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
    @Autowired
    private NotificationService notificationService;
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
        notificationService.sendNotification(TransactionType.SAVING_ACCOUNT_CREATED, account.getCustomer().getDocument());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{accountNumber}")
                .buildAndExpand(savedAccount.getAccountNumber()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/accounts/{accountNumber}")
    public ResponseEntity<Object> updateAccountBalance(@RequestBody Account account, @PathVariable String accountNumber) {
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
