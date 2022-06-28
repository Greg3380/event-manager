package com.synerise.eventmanager.persistance;

import com.synerise.eventmanager.persistance.Storage;
import com.synerise.eventmanager.registration.model.Event;
import com.synerise.eventmanager.registration.model.EventWithTimestamp;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EventRepository {
    private final Storage<EventWithTimestamp> storage;

    public EventRepository(Storage<EventWithTimestamp> storage) {
        this.storage = storage;
    }

    public boolean saveEvent(Event event) {
        return this.storage.storeEvent(event);
    }

    public Map<String, List<EventWithTimestamp>> getItems() {
        return storage.getItems();
    }

}
