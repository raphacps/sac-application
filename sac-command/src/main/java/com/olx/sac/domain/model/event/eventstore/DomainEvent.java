package com.olx.sac.domain.model.event.eventstore;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.olx.sac.infrastructure.json.JsonUtil;

import java.io.Serializable;
import java.util.UUID;

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
    default AggregateId getAggregateId() {
        return new AggregateId(UUID.randomUUID().toString());
    }
}
