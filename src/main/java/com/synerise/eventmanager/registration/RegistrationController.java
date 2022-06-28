package com.synerise.eventmanager.registration;

import com.synerise.eventmanager.registration.model.RegisterEventRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationServiceImpl registrationService;

    public RegistrationController(RegistrationServiceImpl registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/event")
    public ResponseEntity<Boolean> registerEvent(@RequestBody RegisterEventRequest registerEventRequest) {
        try {
            boolean result = registrationService.registerEvent(registerEventRequest);
            return ResponseEntity.ok(result);
        } catch (RuntimeException exception) {
            return ResponseEntity.internalServerError().body(Boolean.FALSE);
        }
    }

}
