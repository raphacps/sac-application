package com.olx.sac.api.attendance.queue;

import com.olx.sac.api.attendance.to.IssueTO;
import com.olx.sac.infrastructure.messaging.amqp.EventsDestination;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * Created by raphael on 17/10/17.
 */
@EnableBinding(EventsDestination.class)
public class AttendanceEventQueue {

    @StreamListener(EventsDestination.SAVE_ISSUE_INPUT)
    public void saveIssue(IssueTO issueTO) {

    }
}
