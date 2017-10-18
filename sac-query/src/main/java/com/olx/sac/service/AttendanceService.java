package com.olx.sac.service;

import com.olx.sac.entity.Issue;
import com.olx.sac.entity.IssueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by raphael on 17/10/17.
 */
@AllArgsConstructor
@Service
public class AttendanceService {
    private IssueRepository issueRepository;

    public List<Issue> findAllIssues() {
        return issueRepository.findAllOrderByCreatedAt();
    }

    public void save(Issue issue) {
        issueRepository.save(issue);
    }
}
