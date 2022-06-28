package com.synerise.eventmanager.registration.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EventWithTimestamp {

    private final String eventId;
    private final LocalDateTime timestamp;

    public EventWithTimestamp(Event event) {
        this.eventId = event.getEventId();
        this.timestamp = LocalDateTime.now();
    }
}
