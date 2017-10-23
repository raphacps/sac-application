package com.olx.sac.domain.model.issue;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import lombok.Value;

/**
 * Created by raphael on 13/10/17.
 */
@Value
public class Reason {

    @NonNull
    @JsonProperty("reason")
    private String content;
}
