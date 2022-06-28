package com.synerise.eventmanager.purge;


import com.synerise.eventmanager.persistance.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledEventPurge {

    private final EventRepository eventRepository;

    @Value("${event.max.lifetime:60}")
    private Integer maxEventLifetime;

    @Scheduled(cron = "*/5 * * * * *")
    public void schedulePurgeTask() {
        eventRepository.purgeOutdatedEvents(maxEventLifetime);
    }
}
