package com.synerise.eventmanager.registration;

import com.synerise.eventmanager.mapper.EventMapper;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final EventMapper eventMapper;

    public RegistrationService(RegistrationRepository registrationRepository, EventMapper eventMapper) {
        this.registrationRepository = registrationRepository;
        this.eventMapper = eventMapper;
    }

    public void registerEvent(RegisterEventRequest request) {
        this.registrationRepository.saveEvent(
                this.eventMapper.map(request)
        );
    }

}
