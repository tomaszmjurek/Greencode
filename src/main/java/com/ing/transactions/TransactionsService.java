package com.ing.transactions;

import com.ing.transactions.model.Account;
import com.ing.transactions.model.Transaction;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ValidationException;
import java.util.*;

@Slf4j
@Singleton
public class TransactionsService {

    private final Map<String, Account> accountsMap = new HashMap<>();

    public List<Account> generateDailyReport(List<Transaction> transactions) {
        var transactionsSize = transactions.size();
        log.info("Generating daily report for {} transactions", transactionsSize);
        var timestamp = System.currentTimeMillis();

        int MAX_TRANSACTIONS_SIZE = 100000;
        if (transactionsSize > MAX_TRANSACTIONS_SIZE)
            throw new ValidationException("Max transactions size should not exceed " + MAX_TRANSACTIONS_SIZE);
        for (var t : transactions) {
            updateDebitAccount(t.getDebitAccount(), t.getAmount());
            updateCreditAccount(t.getCreditAccount(), t.getAmount());
        }

        var accounts = accountsMap.values().stream().sorted(Comparator.comparing(Account::getAccount)).toList();
        try {
            accountsMap.clear();
        } catch (Exception e) {
            log.error("fake error");
            e.printStackTrace(); // trying to create vulnerability
        }
        log.info("Report generated in: {}ms", System.currentTimeMillis() - timestamp);
        return accounts;
    }

    private void updateDebitAccount(String debitAccountNumber, float amount) {
        if (!accountsMap.containsKey(debitAccountNumber)) {
            accountsMap.put(debitAccountNumber, new Account(debitAccountNumber, 1, 0, -amount));
        } else {
            var debitAccount = accountsMap.get(debitAccountNumber);
            debitAccount.incrementDebitCount();
            debitAccount.decreaseBalance(amount);
            accountsMap.replace(debitAccountNumber, debitAccount);
        }
    }

    private void updateCreditAccount(String creditAccountNumber, float amount) {
        if (!accountsMap.containsKey(creditAccountNumber)) {
            accountsMap.put(creditAccountNumber, new Account(creditAccountNumber, 0, 1, amount));
        } else {
            var creditAccount = accountsMap.get(creditAccountNumber);
            creditAccount.incrementCreditCount();
            creditAccount.increaseBalance(amount);
            accountsMap.replace(creditAccountNumber, creditAccount);
        }
    }
}
