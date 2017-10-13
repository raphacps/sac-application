package com.olx.sac.domain.model.event.eventstore;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * Created by raphael on 21/11/16.
 */

@Table(indexes = @Index(name = "aggregateIndex", columnList = "aggregate, aggregateId, createdAt"))
@Entity
@EntityListeners(AuditingEntityListener.class)
@ToString
@EqualsAndHashCode
public final class EventStore<T extends DomainEvent> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String eventId;

    @Embedded
    private AggregateId aggregateId;

    @NotNull
    @Type(type = "jsonObject")
    @Column(columnDefinition = "jsonb", name = "event")
    private T jsonObject;

    @NotNull
    private String eventType;

    @CreatedDate
    private Date createdAt;

    private String aggregate;

    EventStore() {
    }

    public EventStore(T jsonObject) {
        this.jsonObject = jsonObject;
        this.eventType = jsonObject.getClass().getCanonicalName();
        this.aggregate = jsonObject.getAggregate().getCanonicalName();
        this.aggregateId = jsonObject.getAggregateId();
        this.eventId = UUID.randomUUID().toString();
    }

    public T event() {
        return jsonObject;
    }

    public Date createdAt() {
        return createdAt;
    }

}
