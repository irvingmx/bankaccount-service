package irvingmx.bank.domain;

import irvingmx.bank.enums.TransactionType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue
    private Integer transactionId;
    private Timestamp transactionTimestamp;
    private TransactionType transactionType;
    @ManyToOne
    private Customer customer;

}
