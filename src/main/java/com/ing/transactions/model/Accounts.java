package com.ing.transactions.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Accounts {

    @JsonValue
    List<Account> accounts;
}
