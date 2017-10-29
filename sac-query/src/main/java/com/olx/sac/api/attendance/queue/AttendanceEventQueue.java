package com.olx.sac.api.attendance.queue;

import com.olx.sac.entity.Issue;
import com.olx.sac.infrastructure.messaging.amqp.EventsDestination;
import com.olx.sac.service.AttendanceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * Created by raphael on 17/10/17.
 */
@AllArgsConstructor
@Slf4j
@EnableBinding(EventsDestination.class)
public class AttendanceEventQueue {



    private AttendanceService attendanceService;

    @StreamListener(EventsDestination.SAVE_ISSUE_INPUT)
    public void saveIssue(Issue issue) {
        log.info("recebendo issue via queue {}", issue);
        attendanceService.save(issue);
    }


    public static void main(String[] args) {
        System.out.println((int)9/2);
    }
}
