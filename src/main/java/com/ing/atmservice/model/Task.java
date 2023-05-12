package com.ing.atmservice.model;

import io.micronaut.core.annotation.Introspected;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Introspected
public class Task {
    @Min(1) @Max(9999)
    private int region;
    RequestType requestType;
    @Min(1) @Max(9999)
    private int atmId;

    public enum RequestType {
        FAILURE_RESTART(0), PRIORITY(1), SIGNAL_LOW(2), STANDARD(3);

        @Getter
        private final Integer order;

        RequestType(int order) {
            this.order = order;
        }
    }
}
