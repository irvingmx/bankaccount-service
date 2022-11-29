package irvingmx.bank.service;

import irvingmx.bank.enums.TransactionType;

public interface NotificationService {

    void sendNotification(TransactionType transaction, String document);

}
