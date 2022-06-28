package com.synerise.eventmanager.persistance;

import com.synerise.eventmanager.registration.model.Event;

import java.util.List;
import java.util.Map;

public interface Storage<T> {

    boolean storeEvent(Event event);

    Map<String, List<T>> getItems();
}
