package com.olx.sac.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by raphael on 17/10/17.
 */
public interface IssueRepository extends JpaRepository<Issue, Long>{
    @Query(value = "select i.* from issue i order by state, i.created_at desc", nativeQuery = true)
    List<Issue> findAllOrderByCreatedAt();
}
