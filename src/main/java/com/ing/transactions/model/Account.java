package com.ing.transactions.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@Data
@Builder
public class Account {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    String account;
    int debitCount;
    int creditCount;
    float balance;

    public Account(String account, int debitCount, int creditCount, float balance) {
        validateAccount(account);
        this.account = account;
        this.debitCount = debitCount;
        this.creditCount = creditCount;
        this.balance = balance;
    }

    public Account(String account) {
        validateAccount(account);
        this.account = account;
        this.debitCount = 0;
        this.creditCount = 0;
        this.balance = 0.0f;
    }

    public void decreaseBalance(float amount) {
        BigDecimal balanceRounded = new BigDecimal(this.balance - amount).setScale(2, RoundingMode.HALF_UP);
        this.balance = balanceRounded.floatValue();
    }

    public void increaseBalance(float amount) {
        BigDecimal balanceRounded = new BigDecimal(this.balance + amount).setScale(2, RoundingMode.HALF_UP);
        this.balance = balanceRounded.floatValue();
    }

    public void incrementCreditCount() {
        this.creditCount += 1;
    }

    public void incrementDebitCount() {
        this.debitCount += 1;
    }

    private void validateAccount(String accountNumber) {
        if (accountNumber.length() != 26) throw new ValidationException("Account number length is incorrect");
    }
}
