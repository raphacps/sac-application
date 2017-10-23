package com.olx.sac.api.attendance.web;

import com.olx.sac.entity.Issue;
import com.olx.sac.service.AttendanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

/**
 * Created by raphael on 13/10/17.
 */

@RestController
@RequestMapping(value = "/issues")
@Slf4j
@AllArgsConstructor
@Api(value = "/issues", description = "Find an attendance")
public class AttendanceController {

    private AttendanceService attendanceService;

    @GetMapping
    @ApiOperation(value = "get a list of issues", code = 200)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Issues not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")}
    )
    public ResponseEntity<List<Issue>> findAllIssues() {
        List<Issue> issues = attendanceService.findAllIssues();
        return status(OK).body(issues);
    }
}
