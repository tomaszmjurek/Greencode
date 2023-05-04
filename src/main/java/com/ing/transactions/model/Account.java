package com.ing.transactions.model;

import lombok.Builder;
import lombok.Data;

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
        this.account = account;
        this.debitCount = debitCount;
        this.creditCount = creditCount;
        this.balance = balance;
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
}
