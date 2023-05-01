package com.ing.onlinegame.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Players {

    @Size(min = 1, max = 1000) // todo make validation work
    int groupCount;
    @Size(max = 20000)
    List<Clan> clans;
}
