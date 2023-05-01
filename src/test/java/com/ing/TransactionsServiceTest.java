package com.ing;

import com.ing.transactions.TransactionsService;
import com.ing.transactions.model.Account;
import com.ing.transactions.model.Transaction;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@MicronautTest
public class TransactionsServiceTest {

    private final TransactionsService transactionsService = new TransactionsService();

    @Test
    void shouldGenerateReport() {
        var transactions = List.of(
                Transaction.builder()
                        .debitAccount("32309111922661937852684864")
                        .creditAccount("06105023389842834748547303")
                        .amount(10.90f)
                        .build(),
               Transaction.builder()
                        .debitAccount("31074318698137062235845814")
                        .creditAccount("66105036543749403346524547")
                        .amount(200.90f)
                        .build(),
               Transaction.builder()
                        .debitAccount("66105036543749403346524547")
                        .creditAccount("32309111922661937852684864")
                        .amount(50.10f)
                        .build());

        List<Account> report = transactionsService.generateDailyReport(transactions);

        var expectedReport = List.of(
                Account.builder()
                        .account("06105023389842834748547303")
                        .debitCount(0)
                        .creditCount(1)
                        .balance(10.90f)
                        .build(),
                Account.builder()
                        .account("31074318698137062235845814")
                        .debitCount(1)
                        .creditCount(0)
                        .balance(-200.90f)
                        .build(),
                Account.builder()
                        .account("32309111922661937852684864")
                        .debitCount(1)
                        .creditCount(1)
                        .balance(39.20f)
                        .build(),
                Account.builder()
                        .account("66105036543749403346524547")
                        .debitCount(1)
                        .creditCount(1)
                        .balance(150.80f)
                        .build());

        Assertions.assertEquals(expectedReport, report);
    }
}
