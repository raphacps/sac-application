package com.olx.sac.infrastructure.event;

import com.olx.sac.domain.model.event.attendance.IssueCreatedEvent;
import com.olx.sac.domain.model.event.eventstore.EventStore;
import com.olx.sac.domain.model.event.eventstore.EventStoreRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by raphael on 13/10/17.
 */
@Component
@AllArgsConstructor
@Slf4j
public class ApplicationEvents {

    private EventStoreRepository eventStoreRepository;

    @EventListener
    public void issueCreated(IssueCreatedEvent issueCreatedEvent) {
        log.info("publishing event {}", issueCreatedEvent);
        eventStoreRepository.save(new EventStore<>(issueCreatedEvent));
    }
}
