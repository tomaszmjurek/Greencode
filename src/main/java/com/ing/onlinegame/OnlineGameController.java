package com.ing.onlinegame;

import com.ing.onlinegame.model.Clan;
import com.ing.onlinegame.model.Players;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import lombok.AllArgsConstructor;

import java.util.List;

@Controller("/onlinegame")
@AllArgsConstructor
public class OnlineGameController {

    private final OnlineGameService onlineGameService;

    @Post("/calculate")
    public List<List<Clan>> calculate(@Body Players players) {
        return onlineGameService.calculate(players);
    }
}
