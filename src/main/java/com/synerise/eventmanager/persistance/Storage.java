package com.synerise.eventmanager.persistance;

import com.synerise.eventmanager.registration.model.Event;

import java.util.Map;
import java.util.Set;

public interface Storage<T> {

    boolean storeEvent(Event event);

    Map<String, Set<T>> getItems();
}
