package com.ing.transactions.model;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Introspected
public class Transaction {
        @Size(min = 26, max = 26, message = "Length must equal 26")
        String debitAccount;
        @Size(min = 26, max = 26, message = "Length must equal 26")
        String creditAccount;
        float amount;
}
