package irvingmx.bank.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Customer {

    @Id
    private String document;
    private String name;
    private String lastName;



}
