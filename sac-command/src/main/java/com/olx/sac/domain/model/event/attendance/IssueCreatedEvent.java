package com.olx.sac.domain.model.event.attendance;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.olx.sac.domain.model.event.eventstore.DomainEvent;
import com.olx.sac.domain.model.issue.Issue;
import lombok.NonNull;
import lombok.Value;

/**
 * Created by raphael on 13/10/17.
 */
@Value
public class IssueCreatedEvent implements DomainEvent<Issue> {

    @NonNull
    @JsonUnwrapped
    private Issue issue;

    @Override
    public Class<Issue> getAggregate() {
        return Issue.class;
    }
}
