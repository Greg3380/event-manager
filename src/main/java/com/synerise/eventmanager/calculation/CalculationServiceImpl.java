package com.synerise.eventmanager.calculation;

import com.synerise.eventmanager.persistance.EventRepository;
import com.synerise.eventmanager.registration.model.EventWithTimestamp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private final EventRepository eventRepository;

    @Override
    public Integer getUniqueUserCountByEvent(String eventId, Integer minutesInPast) {
        Map<String, List<EventWithTimestamp>> items = eventRepository.getItems();
        return Math.toIntExact(items.entrySet().stream()
                .filter(entry -> containsEvent(entry.getValue(), eventId))
                .filter(entry -> filterByTime(entry.getValue(), minutesInPast))
                .count());
    }

    private boolean filterByTime(List<EventWithTimestamp> value, Integer minutesInPast) {
        return value.stream()
                .map(EventWithTimestamp::getTimestamp)
                .anyMatch(dateTime -> LocalDateTime.now().minus(minutesInPast, ChronoUnit.MINUTES).isBefore(dateTime));
    }

    private boolean containsEvent(List<EventWithTimestamp> value, String eventId) {
        return value.stream()
                .map(EventWithTimestamp::getEventId)
                .anyMatch(event -> event.equals(eventId));
    }

    @Override
    public Map<String, Integer> getEventToUserCountMap(Integer minutesInPast) {
        Map<String, Set<String>> eventCounter = new HashMap<>();
        Map<String, List<EventWithTimestamp>> items = eventRepository.getItems();
        items.entrySet().stream()
                .filter(entry -> filterByTime(entry.getValue(), minutesInPast))
                .forEach(entry -> entry.getValue().stream()
                        .forEach(event -> putToCounterMap(eventCounter, entry.getKey(), event)));

    return eventCounter.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().size()));
    }

    private void putToCounterMap(Map<String, Set<String>> eventCounter, String userId, EventWithTimestamp event) {
        eventCounter.computeIfAbsent(userId, k -> new HashSet<>()).add(event.getEventId());
        if(eventCounter.containsKey(userId)) {
            eventCounter.get(userId).add(event.getEventId());
        }
    }
}