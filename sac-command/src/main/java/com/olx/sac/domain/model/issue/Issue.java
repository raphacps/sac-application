package com.olx.sac.domain.model.issue;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.NonNull;
import lombok.Value;

/**
 * Created by raphael on 13/10/17.
 */
@Value
public class Issue {

    @NonNull
    @JsonUnwrapped
    private Type type;

    @NonNull
    @JsonUnwrapped
    private Reason reason;

    @NonNull
    @JsonUnwrapped
    private State state;

    @NonNull
    @JsonUnwrapped
    private Description description;
}
