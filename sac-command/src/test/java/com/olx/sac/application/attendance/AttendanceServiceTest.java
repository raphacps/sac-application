package com.olx.sac.application.attendance;

import com.olx.factory.IssueTOFactory;
import com.olx.sac.domain.model.event.attendance.IssueCreatedEvent;
import com.olx.sac.domain.model.issue.*;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by raphael on 22/10/17.
 */
@RunWith(SpringRunner.class)
public class AttendanceServiceTest {

    @InjectMocks
    private AttendanceService attendanceService;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    public void shouldPublishIssueCreatedEvent() throws Exception {
        Issue issue = new Issue(new Type("CHAT"),
                new Reason("Elogios"),
                new State("RJ"),
                new Description(RandomStringUtils.randomAlphabetic(500)));

        attendanceService.register(issue);
        verify(applicationEventPublisher).publishEvent(any(IssueCreatedEvent.class));
    }

}