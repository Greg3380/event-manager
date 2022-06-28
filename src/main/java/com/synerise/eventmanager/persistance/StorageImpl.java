package com.synerise.eventmanager.persistance;

import com.synerise.eventmanager.registration.model.Event;
import com.synerise.eventmanager.registration.model.UserIdWithTimestamp;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class StorageImpl implements Storage<UserIdWithTimestamp> {

    private final HashMap<String, Set<UserIdWithTimestamp>> eventToUsersMap = new HashMap<>();

    @Override
    public boolean storeEvent(Event event) {
        if(eventToUsersMap.containsKey(event.getEventId())){
            eventToUsersMap.get(event.getEventId()).add(new UserIdWithTimestamp(event));
        } else {
            eventToUsersMap.computeIfAbsent(event.getEventId(), k-> new HashSet<>())
                    .add(new UserIdWithTimestamp(event));
        }
        return true;
    }

    @Override
    public Map<String, Set<UserIdWithTimestamp>> getItems() {
        return eventToUsersMap;
    }


}
