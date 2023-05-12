package com.ing;

import com.ing.atmservice.AtmService;
import com.ing.atmservice.model.Atm;
import com.ing.atmservice.model.Order;
import com.ing.atmservice.model.Task;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@MicronautTest
public class AtmServiceTest {

    // JMeter: infinite loop no problem for 50 lines json ~30000 and still going
    private final AtmService atmService = new AtmService();

    @Test
    void shouldOrderScenario1() {
        var request = List.of(
                Task.builder()
                        .region(4)
                        .requestType(Task.RequestType.STANDARD)
                        .atmId(1)
                        .build(),
                Task.builder()
                        .region(1)
                        .requestType(Task.RequestType.STANDARD)
                        .atmId(1)
                        .build(),
                Task.builder()
                        .region(2)
                        .requestType(Task.RequestType.STANDARD)
                        .atmId(1)
                        .build(),
                Task.builder()
                        .region(3)
                        .requestType(Task.RequestType.PRIORITY)
                        .atmId(2)
                        .build(),
                Task.builder()
                        .region(3)
                        .requestType(Task.RequestType.STANDARD)
                        .atmId(1)
                        .build(),
                Task.builder()
                        .region(2)
                        .requestType(Task.RequestType.SIGNAL_LOW)
                        .atmId(1)
                        .build(),
                Task.builder()
                        .region(5)
                        .requestType(Task.RequestType.STANDARD)
                        .atmId(2)
                        .build(),
                Task.builder()
                        .region(5)
                        .requestType(Task.RequestType.FAILURE_RESTART)
                        .atmId(1)
                        .build()
        );

        var atmsOrdered = atmService.calculateOrder(request);

        var expected = new Order(List.of(
                Atm.builder()
                        .region(1)
                        .atmId(1)
                        .build(),
                Atm.builder()
                        .region(2)
                        .atmId(1)
                        .build(),
                Atm.builder()
                        .region(3)
                        .atmId(2)
                        .build(),
                Atm.builder()
                        .region(3)
                        .atmId(1)
                        .build(),
                Atm.builder()
                        .region(4)
                        .atmId(1)
                        .build(),
                Atm.builder()
                        .region(5)
                        .atmId(1)
                        .build(),
                Atm.builder()
                        .region(5)
                        .atmId(2)
                        .build()
        ));

        Assertions.assertEquals(expected, atmsOrdered);
    }

    @Test
    void shouldOrderScenario2() {
        var request = List.of(
                Task.builder()
                        .region(1)
                        .requestType(Task.RequestType.STANDARD)
                        .atmId(2)
                        .build(),
                Task.builder()
                        .region(1)
                        .requestType(Task.RequestType.STANDARD)
                        .atmId(1)
                        .build(),
                Task.builder()
                        .region(2)
                        .requestType(Task.RequestType.PRIORITY)
                        .atmId(3)
                        .build(),
                Task.builder()
                        .region(3)
                        .requestType(Task.RequestType.STANDARD)
                        .atmId(4)
                        .build(),
                Task.builder()
                        .region(4)
                        .requestType(Task.RequestType.STANDARD)
                        .atmId(5)
                        .build(),
                Task.builder()
                        .region(5)
                        .requestType(Task.RequestType.PRIORITY)
                        .atmId(2)
                        .build(),
                Task.builder()
                        .region(5)
                        .requestType(Task.RequestType.STANDARD)
                        .atmId(1)
                        .build(),
                Task.builder()
                        .region(3)
                        .requestType(Task.RequestType.SIGNAL_LOW)
                        .atmId(2)
                        .build(),
                Task.builder()
                        .region(2)
                        .requestType(Task.RequestType.SIGNAL_LOW)
                        .atmId(1)
                        .build(),
                Task.builder()
                        .region(3)
                        .requestType(Task.RequestType.FAILURE_RESTART)
                        .atmId(1)
                        .build()
        );

        var atmsOrdered = atmService.calculateOrder(request);

        var expected = new Order(List.of(
                Atm.builder()
                        .region(1)
                        .atmId(2)
                        .build(),
                Atm.builder()
                        .region(1)
                        .atmId(1)
                        .build(),
                Atm.builder()
                        .region(2)
                        .atmId(3)
                        .build(),
                Atm.builder()
                        .region(2)
                        .atmId(1)
                        .build(),
                Atm.builder()
                        .region(3)
                        .atmId(1)
                        .build(),
                Atm.builder()
                        .region(3)
                        .atmId(2)
                        .build(),
                Atm.builder()
                        .region(3)
                        .atmId(4)
                        .build(),
                Atm.builder()
                        .region(4)
                        .atmId(5)
                        .build(),
                Atm.builder()
                        .region(5)
                        .atmId(2)
                        .build(),
                Atm.builder()
                        .region(5)
                        .atmId(1)
                        .build()
        ));

        Assertions.assertEquals(expected, atmsOrdered);
    }
}