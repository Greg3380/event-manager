package com.synerise.eventmanager.persistance;

import com.synerise.eventmanager.registration.model.Event;
import com.synerise.eventmanager.registration.model.UserIdWithTimestamp;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;

@Repository
public class EventRepository {
    private final Storage<UserIdWithTimestamp> storage;

    public EventRepository(Storage<UserIdWithTimestamp> storage) {
        this.storage = storage;
    }

    public boolean saveEvent(Event event) {
        return this.storage.storeEvent(event);
    }

    public Map<String, Set<UserIdWithTimestamp>> getItems() {
        return storage.getItems();
    }

    public void purgeOutdatedEvents(Integer minutesInPast) {
        storage.getItems().forEach((k,v) -> removeOutdatedEvents(v, minutesInPast));
    }

    private void removeOutdatedEvents(Set<UserIdWithTimestamp> events, Integer minutesInPast) {
        events.removeIf(event -> LocalDateTime.now().minus(minutesInPast, ChronoUnit.MINUTES).isAfter(event.getTimestamp()));
    }

}
