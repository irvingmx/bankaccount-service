package irvingmx.bank.resource;

import irvingmx.bank.domain.CreditCard;
import irvingmx.bank.domain.Customer;
import irvingmx.bank.domain.Score;
import irvingmx.bank.exception.CreditCardRegisteredException;
import irvingmx.bank.exception.UnqualifiedScore;
import irvingmx.bank.model.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class CreditCardResource {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @GetMapping("credit-cards/{document}")
    public List<CreditCard> getAllCreditCardsOfCustomer(@PathVariable String document){
        Customer customer = new Customer();
        customer.setDocument(document);
        return creditCardRepository.findByCustomer(customer);

    }

    @PostMapping("/credit-cards/{document}")
    public ResponseEntity<Object> createCreditCard(@RequestBody CreditCard creditCard, @PathVariable String document) {
        Optional<CreditCard> optionalCreditCard = creditCardRepository.findById(creditCard.getCreditCardNumber());
        if(optionalCreditCard.isPresent()){
            throw new CreditCardRegisteredException("There is already a Credit Card registered with this card number -> " + creditCard.getCreditCardNumber());
        }
        Score score = getCustomerScore(document);
        if(score.getScore() < 50) {
            throw new UnqualifiedScore("This customer has not qualified score for get a Credit Card " + document);
        }
        Customer customer = new Customer();
        customer.setDocument(document);
        creditCard.setCustomer(customer);
        CreditCard savedCreditCard = creditCardRepository.save(creditCard);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{creditCardNumber}")
                .buildAndExpand(savedCreditCard.getCreditCardNumber()).toUri();
        return ResponseEntity.created(location).build();

    }

    public Score getCustomerScore(String document) {
        String uri = "http://localhost:8080/scores/"+document;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, Score.class);
    }




}
