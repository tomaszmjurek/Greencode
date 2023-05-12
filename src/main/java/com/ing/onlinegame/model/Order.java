package com.ing.onlinegame.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Order {
    @JsonValue
    private final List<Group> groups;

    public void addGroup(Group group) {
        this.groups.add(group);
    }
}
