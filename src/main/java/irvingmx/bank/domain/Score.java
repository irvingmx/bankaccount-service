package irvingmx.bank.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Score {

    @Id
    private String document;
    private int score;

}
