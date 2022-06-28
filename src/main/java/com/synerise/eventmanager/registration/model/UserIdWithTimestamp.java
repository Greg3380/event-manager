package com.synerise.eventmanager.registration.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
public class UserIdWithTimestamp {

    private final String userId;
    @EqualsAndHashCode.Exclude
    private final LocalDateTime timestamp;

    public UserIdWithTimestamp(Event event) {
        this.userId = event.getUserId();
        this.timestamp = LocalDateTime.now();
    }

}
