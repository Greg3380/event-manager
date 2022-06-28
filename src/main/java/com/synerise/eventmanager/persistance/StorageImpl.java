package com.synerise.eventmanager.persistance;

import com.synerise.eventmanager.registration.model.Event;
import com.synerise.eventmanager.registration.model.EventWithTimestamp;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StorageImpl implements Storage<EventWithTimestamp> {

    private final HashMap<String, List<EventWithTimestamp>> userToEventMap = new HashMap<>();

    @Override
    public boolean storeEvent(Event event) {
        userToEventMap.computeIfAbsent(event.getUserId(), k-> new ArrayList<>())
                .add(new EventWithTimestamp(event));

        if(userToEventMap.containsKey(event.getEventId())){
            userToEventMap.get(event.getEventId()).add(new EventWithTimestamp(event));
        }
        return true;
    }

    @Override
    public Map<String, List<EventWithTimestamp>> getItems() {
        return userToEventMap;
    }


}
