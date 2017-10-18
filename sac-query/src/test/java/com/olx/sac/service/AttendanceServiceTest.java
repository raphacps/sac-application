package com.olx.sac.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olx.sac.entity.Issue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by raphael on 18/10/17.
 */
@RunWith(SpringRunner.class)
public class AttendanceServiceTest {

    @Value(value = "classpath:desorderedIssueList.json")
    private Resource desorderedIssueList;

    @Value(value = "classpath:orderedIssueList.json")
    private Resource orderedIssueList;

    private List<Issue> desorderedIssues;
    private List<Issue> orderedIssues;

    @Before
    public void setUp() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        desorderedIssues = objectMapper.readValue(desorderedIssueList.getInputStream(), new TypeReference<List<Issue>>(){});
        orderedIssues = objectMapper.readValue(orderedIssueList.getInputStream(), new TypeReference<List<Issue>>(){});
    }

    @Test
    public void findAllIssues() throws Exception {

    }

}