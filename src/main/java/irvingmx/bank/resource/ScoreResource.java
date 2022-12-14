package irvingmx.bank.resource;

import irvingmx.bank.api.v1.BankApiScoreService;
import irvingmx.bank.domain.Score;
import irvingmx.bank.exception.ScoreNotFoundException;
import irvingmx.bank.model.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class ScoreResource implements BankApiScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    @GetMapping("/scores/{document}")
    public Score getScore(@PathVariable String document){
        Optional<Score> optionalScore = scoreRepository.findById(document);
        if(!optionalScore.isPresent()){
            throw new ScoreNotFoundException("Score for " + document + " not found!!!");
        }
        return optionalScore.get();
    }

}
