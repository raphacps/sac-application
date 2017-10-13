package com.olx.sac.domain.model.event.eventstore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * Created by raphael on 03/05/17.
 */

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class AggregateId implements Serializable {

    @NonNull
    @Column(name = "aggregateId", nullable = false)
    @JsonProperty("aggregateId")
    private String valor;

    public String asString() {
        return valor;
    }
}
