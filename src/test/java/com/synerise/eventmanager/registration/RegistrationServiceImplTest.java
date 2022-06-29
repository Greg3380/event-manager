package com.synerise.eventmanager.registration;

import com.synerise.eventmanager.mapper.EventMapper;
import com.synerise.eventmanager.persistance.EventRepository;
import com.synerise.eventmanager.registration.model.Event;
import com.synerise.eventmanager.registration.model.RegisterEventRequest;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.MockitoAnnotations.openMocks;
@ExtendWith(MockitoExtension.class)
class RegistrationServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventMapper eventMapper;

    private RegistrationService registrationService;

    @BeforeEach
    public void before() {
        registrationService = new RegistrationServiceImpl(eventRepository, eventMapper);
    }

    @Test
    public void registerEvent() {
        when(eventRepository.saveEvent(any()))
                .thenReturn(Boolean.TRUE);
        when(eventMapper.map(any()))
                .thenReturn(new Event("aa", "bb"));
        Boolean result = registrationService.registerEvent(new RegisterEventRequest("aa", "aa"));
        assertThat(result).isTrue();
    }
}