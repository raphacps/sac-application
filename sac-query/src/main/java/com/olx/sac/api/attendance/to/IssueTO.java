package com.olx.sac.api.attendance.to;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class IssueTO {

    @NotBlank
    private String description;

    @NotBlank
    private String reason;

    @NotBlank
    private String state;

    @NotBlank
    private String type;
}