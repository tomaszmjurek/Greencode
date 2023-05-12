package com.ing.atmservice.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Order {
    @JsonValue
    private final List<Atm> atms;
}
