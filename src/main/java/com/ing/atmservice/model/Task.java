package com.ing.atmservice.model;

import lombok.*;

import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Size(min = 1, max = 9999)
    int region;
    RequestType requestType;
    @Size(min = 1, max = 9999)
    int atmId;

    public enum RequestType {
        FAILURE_RESTART(0), PRIORITY(1), SIGNAL_LOW(2), STANDARD(3);

        @Getter
        private final Integer order;

        RequestType(int order) {
            this.order = order;
        }
    }
}
