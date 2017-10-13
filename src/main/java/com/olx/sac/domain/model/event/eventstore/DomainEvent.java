package com.olx.sac.domain.model.event.eventstore;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.m4u.eventsourcing.infrastructure.json.JsonUtil;

import java.io.Serializable;

/**
 * Created by raphael on 18/11/16.
 */
public interface DomainEvent<T> extends Serializable {

    default String toJson() {
        return JsonUtil.toJson(this);
    }

    @JsonIgnore
    Class<T> getAggregate();

    @JsonIgnore
    AggregateId getAggregateId();
}
