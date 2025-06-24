package com.firstclub.membership.dao.impl;

import com.firstclub.membership.dao.MembershipPlanDao;
import com.firstclub.membership.data.entity.MembershipPlan;
import com.firstclub.membership.repository.MembershipPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MembershipPlanDaoImpl implements MembershipPlanDao {
    private final MembershipPlanRepository membershipPlanRepository;

    @Override
    public MembershipPlan save(MembershipPlan plan) {
        return membershipPlanRepository.save(plan);
    }

    @Override
    public List<MembershipPlan> findAll() {
        return membershipPlanRepository.findAll();
    }

    @Override
    public Optional<MembershipPlan> findById(String id) {
        return membershipPlanRepository.findById(id);
    }
}
