package irvingmx.bank.resource;

import com.google.gson.Gson;
import irvingmx.bank.domain.Customer;
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

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerResource.class)
public class CustomerResourceTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CustomerRepository customerRepository;
    @MockBean
    private NotificationService notificationService;

    @Test
    public void testGetCustomers() throws Exception {
        given(this.customerRepository.findAll())
                .willReturn(new ArrayList<>());
        this.mvc.perform(get("/customers/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testPostCustomers() throws Exception {
        Customer customer = new Customer();
        customer.setDocument("12345678Z");
        customer.setName("TestName");
        customer.setLastName("TestLastName");
        customer.setEmail("iramirezg@gmail.com");
        given(this.customerRepository.save(customer))
                .willReturn(customer);
        Gson gson = new Gson();
        String json = gson.toJson(customer);
        this.mvc.perform(post("/customers/").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void testPutCustomers() throws Exception {
        Customer customer = new Customer();
        customer.setDocument("12345678Z");
        customer.setName("TestName");
        customer.setLastName("TestLastName");
        customer.setEmail("iramirezg@gmail.com");
        given(this.customerRepository.findById("12345678Z"))
                .willReturn(Optional.of(customer));
        given(this.customerRepository.save(customer))
                .willReturn(customer);
        Gson gson = new Gson();
        String json = gson.toJson(customer);
        this.mvc.perform(put("/customers/12345678Z").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().is2xxSuccessful());
    }
}