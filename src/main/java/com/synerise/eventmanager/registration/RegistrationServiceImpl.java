package com.synerise.eventmanager.registration;

import com.synerise.eventmanager.mapper.EventMapper;
import com.synerise.eventmanager.persistance.EventRepository;
import com.synerise.eventmanager.registration.model.RegisterEventRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final EventRepository registrationRepository;
    private final EventMapper eventMapper;

    public RegistrationServiceImpl(EventRepository registrationRepository, EventMapper eventMapper) {
        this.registrationRepository = registrationRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public Boolean registerEvent(RegisterEventRequest request) {
        return this.registrationRepository.saveEvent(
                this.eventMapper.map(request));
    }

}
