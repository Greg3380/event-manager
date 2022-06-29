package com.synerise.eventmanager.calculation;

import com.synerise.eventmanager.persistance.EventRepository;
import com.synerise.eventmanager.registration.RegistrationServiceImpl;
import com.synerise.eventmanager.registration.model.Event;
import com.synerise.eventmanager.registration.model.UserIdWithTimestamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculationServiceTest {

    @Mock
    private EventRepository eventRepository;

    private CalculationService calculationService;

    @BeforeEach
    public void before() {
        calculationService = new CalculationServiceImpl(eventRepository);
    }
    @Test
    void getUniqueUserCountByEvent() {
        Set<UserIdWithTimestamp> usersWithTimestamp = new HashSet<>();
        usersWithTimestamp.add(new UserIdWithTimestamp(new Event("event1", "user1")));
        Map<String, Set<UserIdWithTimestamp>> events = new HashMap<>();
        events.put("event1", usersWithTimestamp);

        when(eventRepository.getItems()).thenReturn(events);

        Integer result = calculationService.getUniqueUserCountByEvent("event1", 10);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void getUniqueUserCountByEvent_mismatchEventId() {
        Set<UserIdWithTimestamp> usersWithTimestamp = new HashSet<>();
        usersWithTimestamp.add(new UserIdWithTimestamp(new Event("event1", "user1")));
        Map<String, Set<UserIdWithTimestamp>> events = new HashMap<>();
        events.put("event1", usersWithTimestamp);

        when(eventRepository.getItems()).thenReturn(events);

        Integer result = calculationService.getUniqueUserCountByEvent("event2", 10);
        assertThat(result).isEqualTo(0);
    }

    @Test
    void getEventToUserCountMap() {
        Set<UserIdWithTimestamp> usersWithTimestamp = new HashSet<>();
        usersWithTimestamp.add(new UserIdWithTimestamp(new Event("event1", "user1")));
        Map<String, Set<UserIdWithTimestamp>> events = new HashMap<>();
        events.put("event1", usersWithTimestamp);

        when(eventRepository.getItems()).thenReturn(events);

        Map<String, Integer> eventToUserCountMap = calculationService.getEventToUserCountMap(10);
        assertThat(eventToUserCountMap.size()).isEqualTo(1);
    }
}