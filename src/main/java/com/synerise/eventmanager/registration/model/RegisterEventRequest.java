package com.synerise.eventmanager.registration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class RegisterEventRequest {

    private String eventId;
    private String userId;

    public RegisterEventRequest(String eventId, String userId) {
        this.eventId = eventId;
        this.userId = userId;
    }

    public RegisterEventRequest() {
    }

    public String getEventId() {
        return eventId;
    }

    public String getUserId() {
        return userId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
