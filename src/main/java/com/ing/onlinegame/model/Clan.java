package com.ing.onlinegame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Introspected
public class Clan {

    @Min(1) @Max(1000)
    int numberOfPlayers;
    @Min(1) @Max(1000000)
    int points;

    @JsonIgnore
    public float getPerPlayerScore() {
        return (float) points / numberOfPlayers;
    }
}
