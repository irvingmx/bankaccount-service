package irvingmx.bank.api.v1;

import irvingmx.bank.domain.Score;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public interface BankApiScoreService {
    @GetMapping("/scores/{document}")
    Score getScore(@PathVariable String document);
}
