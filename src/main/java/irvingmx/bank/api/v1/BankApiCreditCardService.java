package irvingmx.bank.api.v1;

import irvingmx.bank.domain.CreditCard;
import irvingmx.bank.domain.Score;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/v1")
public interface BankApiCreditCardService {
    @GetMapping("credit-cards/{document}")
    List<CreditCard> getAllCreditCardsOfCustomer(@PathVariable String document);

    @PostMapping("/credit-cards/{document}")
    ResponseEntity<Object> createCreditCard(@RequestBody CreditCard creditCard, @PathVariable String document);

    Score getCustomerScore(String document);
}
