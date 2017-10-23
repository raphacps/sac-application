package com.olx.sac.infrastructure.messaging.amqp;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

/**
 * Created by raphael on 16/10/17.
 */
public interface EventsDestination {
    String SAVE_ISSUE_INPUT = "saveIssueInput";

    @Input(SAVE_ISSUE_INPUT)
    MessageChannel saveIssueInput();
}
