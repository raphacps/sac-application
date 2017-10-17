package com.olx.sac.api.attendance.web;

import com.olx.factory.IssueFactory;
import com.olx.sac.api.attendance.web.facade.AttendanceServiceFacade;
import com.olx.sac.api.attendance.web.facade.to.IssueTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.olx.sac.infrastructure.json.JsonUtil.toJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by raphael on 16/10/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AttendanceControllerIT {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AttendanceServiceFacade attendanceServiceFacade;

    @Test
    public void shouldCreateIssueWithSuccess() throws Exception {
        this.mvc.perform(post("/issues").accept(MediaType.APPLICATION_JSON)
                .content(toJson(IssueFactory.create()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void shouldNotCreateIssueWithoutAnyAttribute() throws Exception {
        IssueTO issueTOWithoutType = IssueFactory.create();
        issueTOWithoutType.setType(null);
        this.mvc.perform(post("/issues").accept(MediaType.APPLICATION_JSON)
                .content(toJson(issueTOWithoutType))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"code\":\"BAD_REQUEST\",\"message\":\"type may not be empty\"}"));

        IssueTO issueTOWithoutState = IssueFactory.create();
        issueTOWithoutState.setState(null);
        this.mvc.perform(post("/issues").accept(MediaType.APPLICATION_JSON)
                .content(toJson(issueTOWithoutState))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"code\":\"BAD_REQUEST\",\"message\":\"state may not be empty\"}"));

        IssueTO issueTOWithoutReason = IssueFactory.create();
        issueTOWithoutReason.setReason(null);
        this.mvc.perform(post("/issues").accept(MediaType.APPLICATION_JSON)
                .content(toJson(issueTOWithoutReason))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"code\":\"BAD_REQUEST\",\"message\":\"reason may not be empty\"}"));

        IssueTO issueTOWithoutDescription = IssueFactory.create();
        issueTOWithoutDescription.setDescription(null);
        this.mvc.perform(post("/issues").accept(MediaType.APPLICATION_JSON)
                .content(toJson(issueTOWithoutDescription))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"code\":\"BAD_REQUEST\",\"message\":\"description may not be empty\"}"));



    }

}