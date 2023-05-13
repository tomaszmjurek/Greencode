package com.ing.onlinegame;

import com.ing.onlinegame.model.Clan;
import com.ing.onlinegame.model.Players;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Controller("/onlinegame")
@AllArgsConstructor
public class OnlineGameController {

    private final OnlineGameService onlineGameService;

    @Post("/calculate")
    @Operation(description = "Calculate order", operationId = "calculate")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public List<List<Clan>> calculate(@Valid @Body Players players) {
        return onlineGameService.calculate(players);
    }
}
