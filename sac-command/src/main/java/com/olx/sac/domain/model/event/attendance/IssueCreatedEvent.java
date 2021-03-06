package com.olx.sac.domain.model.event.attendance;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.olx.sac.domain.model.event.eventstore.DomainEvent;
import com.olx.sac.domain.model.issue.Issue;
import lombok.NonNull;
import lombok.Value;

import java.util.Date;

/**
 * Created by raphael on 13/10/17.
 */
@Value
public class IssueCreatedEvent implements DomainEvent<Issue> {

    @JsonUnwrapped
    private Issue issue;

    private Date createdAt;

    public IssueCreatedEvent(@NonNull Issue issue) {
        this.issue = issue;
        this.createdAt = new Date();
    }

    @Override
    public Class<Issue> getAggregate() {
        return Issue.class;
    }
}
