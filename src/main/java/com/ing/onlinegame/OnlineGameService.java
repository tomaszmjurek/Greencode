package com.ing.onlinegame;

import com.ing.onlinegame.model.Clan;
import com.ing.onlinegame.model.Group;
import com.ing.onlinegame.model.Players;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Singleton
public class OnlineGameService {

    private final List<List<Clan>> groups = new ArrayList<>();
    public List<List<Clan>> calculate(Players players) {
        var timestamp = System.currentTimeMillis();
        var clansWaiting = new LinkedList<>(sortClansByPointsAndPlayers(players.getClans()));
        log.info("Clans waiting to join: {}", clansWaiting);

        var iterator = clansWaiting.listIterator();
        var currentClan = iterator.next();
        var currentGroup = new Group(players.getGroupCount());
        boolean resetIteration = false;
        while (!clansWaiting.isEmpty()) {
            if (currentGroup.fitsClan(currentClan.getNumberOfPlayers())) {
                currentGroup.addClan(currentClan);
                iterator.remove();
                resetIteration = true;
            }

            if (currentGroup.isFull() || !iterator.hasNext()) {
                groups.add(currentGroup.getClans());
                currentGroup = new Group(players.getGroupCount());
                resetIteration = true;
            }

            if (resetIteration) {
                iterator = clansWaiting.listIterator();
                resetIteration = false;
            }

            if (iterator.hasNext()) currentClan = iterator.next();
        }

        log.info("Order generated in: {}ms", System.currentTimeMillis() - timestamp);
        return groups;
    }

    private List<Clan> sortClansByPointsAndPlayers(List<Clan> unsorted) {
        return unsorted.stream().sorted(Comparator.comparingInt(Clan::getPoints).thenComparing(Clan::getPerPlayerScore).reversed()).toList();
    }
}
