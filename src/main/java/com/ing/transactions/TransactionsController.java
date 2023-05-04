package com.ing.transactions;

import com.ing.transactions.model.Account;
import com.ing.transactions.model.Transaction;
import io.micronaut.context.annotation.Parameter;
import io.micronaut.http.annotation.*;
import lombok.AllArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Controller("/transactions")
@AllArgsConstructor
public class TransactionsController {

    private final TransactionsService transactionsService;

    @Post("/report")
    public List<Account> report(@Valid @Body List<Transaction> reportRequest) {
        return transactionsService.generateDailyReport(reportRequest);
    }

    // todo remove after testing
    @Get("/{size}")
    public List<Transaction> generateInput(@Parameter @PathVariable("size") int size) {
        return transactionsService.generateInput(size);
    }

}