package com.synerise.eventmanager.registration;

import com.synerise.eventmanager.mapper.EventMapper;
import com.synerise.eventmanager.persistance.EventRepository;
import com.synerise.eventmanager.registration.model.RegisterEventRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public RegistrationServiceImpl(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public Boolean registerEvent(RegisterEventRequest request) {
        return this.eventRepository.saveEvent(
                this.eventMapper.map(request));
    }
}
