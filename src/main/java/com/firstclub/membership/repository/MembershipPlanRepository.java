package com.firstclub.membership.repository;

import com.firstclub.membership.data.entity.MembershipPlan;
import com.firstclub.membership.data.enums.MembershipTier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipPlanRepository extends JpaRepository<MembershipPlan, String> {
    List<MembershipPlan> findByTier(MembershipTier tier);
}