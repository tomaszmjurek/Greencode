package com.ing;

import com.ing.onlinegame.model.Clan;
import com.ing.onlinegame.OnlineGameService;
import com.ing.onlinegame.model.Players;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@MicronautTest
public class OnlineGameServiceTest {

    private final OnlineGameService onlineGameService = new OnlineGameService();

    @Test
    void shouldOrderClans() {
        var players = Players.builder()
                .groupCount(6)
                .clans(List.of(
                        Clan.builder()
                                .numberOfPlayers(4)
                                .points(50)
                                .build(),
                        Clan.builder()
                                .numberOfPlayers(2)
                                .points(70)
                                .build(),
                        Clan.builder()
                                .numberOfPlayers(6)
                                .points(60)
                                .build(),
                        Clan.builder()
                                .numberOfPlayers(1)
                                .points(15)
                                .build(),
                        Clan.builder()
                                .numberOfPlayers(5)
                                .points(40)
                                .build(),
                        Clan.builder()
                                .numberOfPlayers(3)
                                .points(45)
                                .build(),
                        Clan.builder()
                                .numberOfPlayers(1)
                                .points(12)
                                .build(),
                        Clan.builder()
                                .numberOfPlayers(4)
                                .points(40)
                                .build()))
                .build();

        List<List<Clan>> groups = onlineGameService.calculate(players);

        var expected = List.of(
                List.of(
                    Clan.builder()
                            .numberOfPlayers(2)
                            .points(70)
                            .build(),
                        Clan.builder()
                            .numberOfPlayers(4)
                            .points(50)
                            .build()
                ),
                List.of(
                    Clan.builder()
                            .numberOfPlayers(6)
                            .points(60)
                            .build()
                ),
                List.of(
                    Clan.builder()
                            .numberOfPlayers(3)
                            .points(45)
                            .build(),
                        Clan.builder()
                            .numberOfPlayers(1)
                            .points(15)
                            .build(),
                        Clan.builder()
                            .numberOfPlayers(1)
                            .points(12)
                            .build()
                ),
                List.of(
                    Clan.builder()
                            .numberOfPlayers(4)
                            .points(40)
                            .build()
                ),
                List.of(
                    Clan.builder()
                            .numberOfPlayers(5)
                            .points(40)
                            .build()
                )
        );

        Assertions.assertEquals(expected, groups);
    }
}
