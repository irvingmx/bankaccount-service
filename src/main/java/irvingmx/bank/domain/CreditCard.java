package irvingmx.bank.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class CreditCard {

    @Id
    private String creditCardNumber;
    private double balance;
    @ManyToOne
    private Customer customer;

}
