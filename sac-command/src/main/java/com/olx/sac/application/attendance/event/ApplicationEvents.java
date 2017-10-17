package com.olx.sac.application.attendance.event;

import com.olx.sac.domain.model.event.attendance.IssueCreatedEvent;
import com.olx.sac.domain.model.event.eventstore.EventStore;
import com.olx.sac.domain.model.event.eventstore.EventStoreRepository;
import com.olx.sac.infrastructure.messaging.amqp.EventsDestination;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by raphael on 13/10/17.
 */
@Component
@AllArgsConstructor
@Slf4j
@EnableBinding(EventsDestination.class)
public class ApplicationEvents {

    private EventStoreRepository eventStoreRepository;
    private EventsDestination eventsDestination;

    @EventListener
    public void issueCreated(IssueCreatedEvent issueCreatedEvent) {
        log.info("publishing event {}", issueCreatedEvent);
        eventStoreRepository.save(new EventStore<>(issueCreatedEvent));
        eventsDestination.issueCreated().send(MessageBuilder.withPayload(issueCreatedEvent).build());
    }
}
