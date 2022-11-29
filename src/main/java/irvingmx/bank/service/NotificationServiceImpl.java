package irvingmx.bank.service;

import irvingmx.bank.domain.Customer;
import irvingmx.bank.domain.Transaction;
import irvingmx.bank.enums.TransactionType;
import irvingmx.bank.model.CustomerRepository;
import irvingmx.bank.model.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Service("NotificationService")
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private EmailService emailService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public void sendNotification(TransactionType transactionType, String document) {
        Optional<Customer> optionalCustomer = customerRepository.findById(document);
        if(optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            Transaction transaction = prepareTransaction(transactionType, customer);
            transactionRepository.save(transaction);
            emailService.sendSimpleMessage(customer.getEmail(), document.toUpperCase() + " - " + transaction.getTransactionType(), transaction.toString());
        }
    }

    private Transaction prepareTransaction(TransactionType transactionType, Customer customer) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionType);
        transaction.setCustomer(customer);
        transaction.setTransactionTimestamp(Timestamp.from(Instant.now()));
        return transaction;
    }
}
