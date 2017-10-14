package com.olx.sac.api.attendance.web.facade.to;

import lombok.Data;

/**
 * Created by raphael on 13/10/17.
 */

@Data
public class IssueTO {
    private String description;
    private String reason;
    private String state;
    private String type;
}
