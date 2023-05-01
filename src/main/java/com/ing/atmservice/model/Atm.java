package com.ing.atmservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@AllArgsConstructor
@Builder
public class Atm implements Comparable<Atm> {
    @Size(min = 1, max = 9999)
    int atmId;
    @Size(min = 1, max = 9999)
    int region;
    @JsonIgnore
    Task.RequestType requestType;

    public boolean isHigherOrder(Atm a) {
        return this.requestType.getOrder() > a.requestType.getOrder();
    }

    @Override
    public int compareTo(Atm a) {
        return this.requestType.getOrder() - a.requestType.getOrder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atm atm = (Atm) o;
        return atmId == atm.atmId && region == atm.region;
    }

    @Override
    public int hashCode() {
        return Objects.hash(atmId, region);
    }
}
