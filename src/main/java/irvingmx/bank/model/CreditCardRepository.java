package irvingmx.bank.model;

import irvingmx.bank.domain.CreditCard;
import irvingmx.bank.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, String> {

    public List<CreditCard> findByCustomer(Customer customer);

}
