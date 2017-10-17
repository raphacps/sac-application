package com.olx.sac.api.attendance.web;

import com.olx.sac.api.attendance.web.facade.AttendanceServiceFacade;
import com.olx.sac.api.attendance.web.facade.to.IssueTO;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@Slf4j
@Api(value = "/issues", description = "Record of attendance")
public class AttendanceController {

    @Autowired
    private AttendanceServiceFacade attendanceServiceFacade;

    @PostMapping
    @ApiOperation(value = "register a issue", code = 201)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid input fields"),
            @ApiResponse(code = 500, message = "Internal Server Error")}
    )
    public ResponseEntity createIssue(@RequestBody @Validated @ApiParam IssueTO issueTO) {
        attendanceServiceFacade.register(issueTO);
        return status(CREATED).build();
    }
}
