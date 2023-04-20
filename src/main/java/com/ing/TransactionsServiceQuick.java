//package com.ing;
//
//import com.ing.model.Account;
//import com.ing.model.Transaction;
//import jakarta.inject.Singleton;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.validation.ValidationException;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Stream;
//import java.util.stream.StreamSupport;
//
//@Slf4j
//@Singleton
//public class TransactionsServiceQuick {
//
//    private final int MAX_TRANSACTIONS_SIZE = 100000;
////    private final Map<String, Account> accountsMap = new HashMap<>(); // stateful data should not be accessed by stream
//
//    public List<Account> generateDailyReport(List<Transaction> transactions) {
//        var timestamp = System.currentTimeMillis();
//
//        if (transactions.size() > MAX_TRANSACTIONS_SIZE) throw new ValidationException("Max transactions size cannot exceed " + MAX_TRANSACTIONS_SIZE);
//
//        Map<String, Account> accountsMap = new HashMap<>();
//        Stream.of(accountsMap, transactions)
//                        .map((m, t) -> updateDebitAccount(m, t))
//
//        transactions.stream()
////                .parallel()
//                .peek(this::validateTransaction)
//                .map(this::updateDebitAccount)
//                .map(this::updateCreditAccount);
//
//        var report = accountsMap.values().stream().sorted(Comparator.comparing(Account::getAccount)).toList();
//
//        log.info("Report generated in: {}ms", System.currentTimeMillis() - timestamp);
//        return report;
//    }
//
//    private void validateTransaction(Transaction t) {
//        if (t.getCreditAccount().length() != 26) throw new ValidationException("Credit account number " + t.getCreditAccount() + " has incorrect size");
//        if (t.getDebitAccount().length() != 26) throw new ValidationException("Debit account number " + t.getDebitAccount() + " has incorrect size");
//    }
//    private Transaction updateDebitAccount(Map<String, Account> accountsMap, Transaction t) {
//        var debitAccountNumber = t.getDebitAccount();
//        var amount = t.getAmount();
//        if (!accountsMap.containsKey(debitAccountNumber)) {
//            accountsMap.put(debitAccountNumber, new Account(debitAccountNumber, 1, 0, -amount));
//        } else {
//            var debitAccount = accountsMap.get(debitAccountNumber);
//            debitAccount.incrementDebitCount();
//            debitAccount.decreaseBalance(amount);
//            accountsMap.replace(debitAccountNumber, debitAccount);
//        }
//        return t;
//    }
//
//    private Transaction updateCreditAccount(Map<String, Account> accountsMap, Transaction t) {
//        var creditAccountNumber = t.getCreditAccount();
//        var amount = t.getAmount();
//        if (!accountsMap.containsKey(creditAccountNumber)) {
//            accountsMap.put(creditAccountNumber, new Account(creditAccountNumber, 0, 1, amount));
//        } else {
//            var creditAccount = accountsMap.get(creditAccountNumber);
//            creditAccount.incrementCreditCount();
//            creditAccount.increaseBalance(amount);
//            accountsMap.replace(creditAccountNumber, creditAccount);
//        }
//        return t;
//    }
//}
