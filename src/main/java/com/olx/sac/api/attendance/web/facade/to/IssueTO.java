package com.olx.sac.api.attendance.web.facade.to;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by raphael on 13/10/17.
 */

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
