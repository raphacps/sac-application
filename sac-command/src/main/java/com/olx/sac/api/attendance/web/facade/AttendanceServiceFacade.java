package com.olx.sac.api.attendance.web.facade;

import com.olx.sac.api.attendance.web.facade.assembler.IssueAssembler;
import com.olx.sac.api.attendance.web.facade.to.IssueTO;
import com.olx.sac.application.attendance.AttendanceService;
import com.olx.sac.domain.model.issue.Issue;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by raphael on 13/10/17.
 */

@AllArgsConstructor
@Component
public class AttendanceServiceFacade {

    @Autowired
    private AttendanceService attendanceService;

    public void register(@NonNull IssueTO issueTO) {
        attendanceService.register(IssueAssembler.toTO(issueTO));
    }
}
