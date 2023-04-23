package com.ing.onlinegame;

import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Singleton
public class OnlineGameService {

    private final List<List<Clan>> groups = new ArrayList<>();
    public List<List<Clan>> calculate(Players players) {
        var clansWaiting = new LinkedList<>(sortClansByPointsAndPlayers(players.clans));
        log.info("Clans waiting to join: {}", clansWaiting);

        var iterator = clansWaiting.listIterator();
        var currentClan = iterator.next();
        var currentGroup = new Group(players.groupCount);
        boolean resetIteration = false;
        while (!clansWaiting.isEmpty()) {
            log.info("Iteration state: current {} current {} final order {}", currentClan, currentGroup, groups);
            if (currentGroup.fitsClan(currentClan.numberOfPlayers)) {
                currentGroup.addClan(currentClan);
                iterator.remove();
                resetIteration = true;
            }

            if (currentGroup.isFull() || !iterator.hasNext()) {
                groups.add(currentGroup.getClans());
                currentGroup = new Group(players.groupCount);
                resetIteration = true;
            }

            if (resetIteration) {
                iterator = clansWaiting.listIterator();
                resetIteration = false;
            }

            if (iterator.hasNext()) currentClan = iterator.next();
        }

        return groups;
    }

    private List<Clan> sortClansByPointsAndPlayers(List<Clan> unsorted) {
        return unsorted.stream().sorted(Comparator.comparingInt(Clan::getPoints).thenComparing(Clan::getPerPlayerScore).reversed()).toList();
    }
}
