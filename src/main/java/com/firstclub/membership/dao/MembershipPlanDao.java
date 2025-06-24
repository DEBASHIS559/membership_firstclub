package com.firstclub.membership.dao;

import com.firstclub.membership.data.entity.MembershipPlan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembershipPlanDao {
    MembershipPlan save(MembershipPlan plan);
    List<MembershipPlan> findAll();
    Optional<MembershipPlan> findById(String id);
}