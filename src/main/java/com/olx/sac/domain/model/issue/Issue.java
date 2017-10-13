package com.olx.sac.domain.model.issue;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.NonNull;
import lombok.Value;

/**
 * Created by raphael on 13/10/17.
 */
@Value
public class Issue {

    @JsonUnwrapped
    private @NonNull Type type;

    @JsonUnwrapped
    private @NonNull Reason reason;

    @JsonUnwrapped
    private @NonNull State state;

    @JsonUnwrapped
    private @NonNull Description description;
}
