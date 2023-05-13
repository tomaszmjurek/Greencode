package com.ing.transactions;

import com.ing.transactions.model.Account;
import com.ing.transactions.model.Transaction;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Controller("/transactions")
@AllArgsConstructor
public class TransactionsController {

    private final TransactionsService transactionsService;

    @Post("/report")
    @Operation(description = "Execute report", operationId = "report")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public List<Account> report(@Valid @Body List<Transaction> reportRequest) {
        return transactionsService.generateDailyReport(reportRequest);
    }
}