package com.synerise.eventmanager.registration.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Event {
    private String eventId;
    private String userId;

    public Event(String eventId, String userId) {
        this.eventId = eventId;
        this.userId = userId;
    }

    public Event() {
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
