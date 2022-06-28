package com.synerise.eventmanager.calculation;

import com.synerise.eventmanager.persistance.EventRepository;
import com.synerise.eventmanager.registration.model.UserIdWithTimestamp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private final EventRepository eventRepository;

    @Override
    public Integer getUniqueUserCountByEvent(String eventId, Integer minutesInPast) {
        Map<String, Set<UserIdWithTimestamp>> items = eventRepository.getItems();
        return Math.toIntExact(items.getOrDefault(eventId, Collections.emptySet()).stream()
                .filter(userIdWithTimestamp -> filterByTime(userIdWithTimestamp.getTimestamp(), minutesInPast))
                .count());
    }

    @Override
    public Map<String, Integer> getEventToUserCountMap(Integer minutesInPast) {
        return eventRepository.getItems().entrySet().stream()
                .filter(entry -> filterByTime(entry.getValue(), minutesInPast))
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().size()));
    }
    private boolean filterByTime(Set<UserIdWithTimestamp> value, Integer minutesInPast) {
        return value.stream()
                .map(UserIdWithTimestamp::getTimestamp)
                .anyMatch(timestamp -> filterByTime(timestamp, minutesInPast));
    }
    private boolean filterByTime(LocalDateTime timestamp, Integer minutesInPast) {
        return LocalDateTime.now().minus(minutesInPast, ChronoUnit.MINUTES).isBefore(timestamp);
    }
}