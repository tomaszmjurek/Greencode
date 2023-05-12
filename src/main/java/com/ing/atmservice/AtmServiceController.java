package com.ing.atmservice;

import com.ing.atmservice.model.Order;
import com.ing.atmservice.model.Task;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Controller("/atms")
public class AtmServiceController {

    private final AtmService atmService;

    @Post("/calculateOrder")
    @Operation(description = "Calculates ATMs order for service team", operationId = "calculate")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public Order calculateOrder(@Valid @Body List<Task> serviceTasks) { // object: ServiceTasks
        return atmService.calculateOrder(serviceTasks);
    }
}
