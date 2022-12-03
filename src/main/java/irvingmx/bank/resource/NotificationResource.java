package irvingmx.bank.resource;

import irvingmx.bank.api.v1.BankApiNotificationService;
import irvingmx.bank.domain.Transaction;
import irvingmx.bank.service.EmailService;
import irvingmx.bank.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class NotificationResource implements BankApiNotificationService {

    @Autowired
    private EmailService emailService;
    @Autowired
    private NotificationService notificationService;

    @PutMapping("/notifications/{document}")
    public ResponseEntity<Object> sendNotification(@RequestBody Transaction transaction, @PathVariable String document) {
        notificationService.sendNotification(transaction.getTransactionType(), document);
        return ResponseEntity.noContent().build();
    }

}
