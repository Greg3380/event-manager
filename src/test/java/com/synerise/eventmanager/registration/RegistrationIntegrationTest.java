package com.synerise.eventmanager.registration;

import com.synerise.eventmanager.EventManagementApplication;
import com.synerise.eventmanager.persistance.EventRepository;
import com.synerise.eventmanager.registration.model.RegisterEventRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = EventManagementApplication.class)
public class RegistrationIntegrationTest {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void saveOneEvent() {
        registrationService.registerEvent(new RegisterEventRequest("1", "1"));
        assertThat(eventRepository.getItems().size()).isEqualTo(1);
    }

    @Test
    public void saveMultipleEventsInParallel() throws InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 100; i++) {
            registrationService.registerEvent(new RegisterEventRequest("event" + i, "user" + i));

        }
        executors.shutdown();
        executors.awaitTermination(10, TimeUnit.MINUTES);
        assertThat(eventRepository.getItems().size()).isEqualTo(100);
        eventRepository.getItems().values()
                .forEach(entry -> assertThat(entry.size()).isEqualTo(1));
    }
}
