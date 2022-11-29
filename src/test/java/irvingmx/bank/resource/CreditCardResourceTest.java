package irvingmx.bank.resource;

import com.google.gson.Gson;
import irvingmx.bank.domain.CreditCard;
import irvingmx.bank.domain.Customer;
import irvingmx.bank.domain.Score;
import irvingmx.bank.model.CreditCardRepository;
import irvingmx.bank.service.NotificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CreditCardResource.class)
public class CreditCardResourceTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private CreditCardRepository creditCardRepository;
    @MockBean
    private NotificationService notificationService;
    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void testGetCreditCards() throws Exception {
        Customer customer = new Customer();
        customer.setDocument("12345678Z");
        given(this.creditCardRepository.findByCustomer(customer))
                .willReturn(new ArrayList<>());
        this.mvc.perform(get("/credit-cards/12345678Z").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }


    @Test
    public void testPostCreditCards() throws Exception {
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber("9876 5432 1234 5698");
        creditCard.setBalance(2500.15);
        Customer customer = new Customer();
        customer.setDocument("12345678Z");
        customer.setName("TestName");
        customer.setLastName("TestLastName");
        customer.setEmail("iramirezg@gmail.com");
        creditCard.setCustomer(customer);
        Score score = new Score();
        score.setDocument(customer.getDocument());
        score.setScore(65);
        given(this.restTemplate.getForObject("http://localhost:8080/scores/"+customer.getDocument(), Score.class))
                .willReturn(score);
        given(this.creditCardRepository.findById(creditCard.getCreditCardNumber()))
                .willReturn(Optional.of(creditCard));
        given(this.creditCardRepository.save(creditCard))
                .willReturn(creditCard);
        Gson gson = new Gson();
        String json = gson.toJson(creditCard);
        this.mvc.perform(post("/credit-cards/").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().is4xxClientError());
    }

}
