package irvingmx.bank.model;

import irvingmx.bank.domain.Customer;
import irvingmx.bank.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByCustomer(Customer customer);

}
