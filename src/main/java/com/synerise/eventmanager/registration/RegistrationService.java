package com.synerise.eventmanager.registration;

import com.synerise.eventmanager.registration.model.RegisterEventRequest;

public interface RegistrationService {
    Boolean registerEvent(RegisterEventRequest request);
}
