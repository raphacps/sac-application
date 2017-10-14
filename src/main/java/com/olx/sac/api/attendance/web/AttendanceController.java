package com.olx.sac.api.attendance.web;

import com.olx.sac.api.attendance.web.facade.AttendanceServiceFacade;
import com.olx.sac.api.attendance.web.facade.to.IssueTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

/**
 * Created by raphael on 13/10/17.
 */

@RestController
@RequestMapping(value = "/issues")
@AllArgsConstructor
@Slf4j
public class AttendanceController {

    private AttendanceServiceFacade attendanceServiceFacade;

    @PostMapping
    public ResponseEntity createIssue(@RequestBody IssueTO issueTO) {
        attendanceServiceFacade.register(issueTO);
        return status(CREATED).build();
    }
}
