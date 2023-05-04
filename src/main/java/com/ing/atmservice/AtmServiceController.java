package com.ing.atmservice;

import com.ing.atmservice.model.Atm;
import com.ing.atmservice.model.Task;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import lombok.AllArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Controller("/atms") // todo api/?
public class AtmServiceController {

    private final AtmService atmService;

    @Post("/calculateOrder")
    public List<Atm> calculateOrder(@Valid @Body List<Task> serviceTasks) {
        return atmService.calculateOrder(serviceTasks);
    }
}
