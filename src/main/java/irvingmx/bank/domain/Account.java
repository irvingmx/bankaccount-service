package irvingmx.bank.domain;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Account {

    @Id
    private String accountNumber;
    private double balance;
    @ManyToOne
    private Customer customer;

}
