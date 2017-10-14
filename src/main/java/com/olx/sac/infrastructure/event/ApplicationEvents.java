package com.olx.sac.infrastructure.event;

import com.olx.sac.domain.model.event.attendance.IssueCreatedEvent;
import com.olx.sac.domain.model.event.eventstore.EventStore;
import com.olx.sac.domain.model.event.eventstore.EventStoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;

/**
 * Created by raphael on 13/10/17.
 */
@AllArgsConstructor
public class ApplicationEvents {

    private EventStoreRepository eventStoreRepository;

    @EventListener
    public void issueCreated(IssueCreatedEvent issueCreatedEvent) {
        eventStoreRepository.save(new EventStore<>(issueCreatedEvent));
    }
}
