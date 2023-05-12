package com.ing.onlinegame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Group {

    @ToString.Exclude
    private int freeSpace;
    @JsonValue
    private List<Clan> clans = new ArrayList<>();

    public Group(int freeSpace) {
        this.freeSpace = freeSpace;
    }

    public Group(List<Clan> clans) {
        this.clans = clans;
    }

    public void addClan(Clan clan) {
        this.freeSpace -= clan.getNumberOfPlayers();
        this.clans.add(clan);
    }

    public boolean fitsClan(int clanSize) {
        return freeSpace >= clanSize;
    }

    @JsonIgnore
    public boolean isFull() {
        return freeSpace == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(clans, group.clans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clans);
    }
}
