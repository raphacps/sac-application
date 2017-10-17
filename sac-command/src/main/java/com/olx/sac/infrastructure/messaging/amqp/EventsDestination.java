package com.olx.sac.infrastructure.messaging.amqp;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by raphael on 16/10/17.
 */
public interface EventsDestination {
    String ISSUE_CREATED_OUTPUT = "issueCreatedOutput";

    @Output(ISSUE_CREATED_OUTPUT)
    MessageChannel issueCreated();
}
