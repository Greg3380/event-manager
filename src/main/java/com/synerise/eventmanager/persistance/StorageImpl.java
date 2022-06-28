package com.synerise.eventmanager.persistance;

import com.synerise.eventmanager.registration.model.Event;
import com.synerise.eventmanager.registration.model.UserIdWithTimestamp;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StorageImpl implements Storage<UserIdWithTimestamp> {

    private final ConcurrentHashMap<String, Set<UserIdWithTimestamp>> eventToUsersMap = new ConcurrentHashMap<>();

    @Override
    public boolean storeEvent(Event event) {
        eventToUsersMap.compute(event.getEventId(), (key, value) -> putEventToMap(value, event));
        return true;
    }

    @Override
    public Map<String, Set<UserIdWithTimestamp>> getItems() {
        return eventToUsersMap;
    }

    private Set<UserIdWithTimestamp> putEventToMap(Set<UserIdWithTimestamp> value, Event event) {
        if(value != null){
            value.add(new UserIdWithTimestamp(event));
            return value;
        }
        Set<UserIdWithTimestamp> usersWithTimestamp = new HashSet<>();
        usersWithTimestamp.add(new UserIdWithTimestamp(event));
        return usersWithTimestamp;
    }
}
