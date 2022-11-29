package irvingmx.bank.resource;

import com.google.gson.Gson;
import irvingmx.bank.domain.Account;
import irvingmx.bank.domain.Customer;
import irvingmx.bank.model.AccountRepository;
import irvingmx.bank.model.CustomerRepository;
import irvingmx.bank.service.NotificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountResource.class)
public class AccountResourceTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private AccountRepository accountRepository;
    @MockBean
    private CustomerRepository customerRepository;
    @MockBean
    private NotificationService notificationService;

    @Test
    public void testGetCustomers() throws Exception {
        given(this.accountRepository.findAll())
                .willReturn(new ArrayList<>());
        this.mvc.perform(get("/accounts/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testPostAccounts() throws Exception {
        Account account = new Account();
        account.setAccountNumber("9876 5432 1234 5698");
        account.setBalance(2500.15);
        Customer customer = new Customer();
        customer.setDocument("12345678Z");
        customer.setName("TestName");
        customer.setLastName("TestLastName");
        customer.setEmail("iramirezg@gmail.com");
        account.setCustomer(customer);
        given(this.customerRepository.findById("12345678Z"))
                .willReturn(Optional.of(customer));
        given(this.accountRepository.save(account))
                .willReturn(account);
        Gson gson = new Gson();
        String json = gson.toJson(account);
        this.mvc.perform(post("/accounts/").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void testPutCustomers() throws Exception {
        Account account = new Account();
        account.setAccountNumber("9876 5432 1234 5698");
        account.setBalance(2500.15);
        Customer customer = new Customer();
        customer.setDocument("12345678Z");
        customer.setName("TestName");
        customer.setLastName("TestLastName");
        customer.setEmail("iramirezg@gmail.com");
        given(this.accountRepository.findById("9876 5432 1234 5698"))
                .willReturn(Optional.of(account));
        given(this.accountRepository.save(account))
                .willReturn(account);
        Gson gson = new Gson();
        String json = gson.toJson(account);
        this.mvc.perform(put("/accounts/9876 5432 1234 5698").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().is2xxSuccessful());
    }
}
