package com.ing.onlinegame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Clan {

    @Size(min = 1, max = 1000)
    int numberOfPlayers;
    @Size(min = 1, max = 1000000)
    int points;

    @JsonIgnore
    public float getPerPlayerScore() {
        return (float) points / numberOfPlayers;
    }
}
