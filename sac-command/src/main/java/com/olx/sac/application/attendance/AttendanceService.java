package com.olx.sac.application.attendance;

import com.olx.sac.domain.model.event.attendance.IssueCreatedEvent;
import com.olx.sac.domain.model.issue.Issue;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Created by raphael on 13/10/17.
 */
@Slf4j
@Service
@AllArgsConstructor
public class AttendanceService {

    private ApplicationEventPublisher applicationEventPublisher;

    public void register(@NonNull Issue issue) {
        log.info("creating issue {}", issue);
        applicationEventPublisher.publishEvent(new IssueCreatedEvent(issue));
    }
}
