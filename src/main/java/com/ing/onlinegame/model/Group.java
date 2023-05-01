package com.ing.onlinegame.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class Group {

    @ToString.Exclude
    int freeSpace;
    List<Clan> clans = new ArrayList<>();

    public Group(int freeSpace) {
        this.freeSpace = freeSpace;
    }

    public void addClan(Clan clan) {
        this.freeSpace -= clan.numberOfPlayers;
        this.clans.add(clan);
    }

    public boolean fitsClan(int clanSize) {
        return freeSpace >= clanSize;
    }

    public boolean isFull() {
        return freeSpace == 0;
    }
}
