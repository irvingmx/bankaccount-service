package irvingmx.bank.resource;

import irvingmx.bank.domain.Account;
import irvingmx.bank.model.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountResource {

    @Autowired
    private AccountRepository bankAccountRepository;

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return bankAccountRepository.findAll();
    }

}
