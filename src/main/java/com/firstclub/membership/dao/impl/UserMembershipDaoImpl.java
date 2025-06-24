package com.firstclub.membership.dao.impl;

import com.firstclub.membership.dao.UserMembershipDao;
import com.firstclub.membership.data.entity.UserMembership;
import com.firstclub.membership.data.enums.MembershipStatus;
import com.firstclub.membership.repository.UserMembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserMembershipDaoImpl implements UserMembershipDao {
    private final UserMembershipRepository userMembershipRepository;

    @Override
    public UserMembership save(UserMembership membership) {
        return userMembershipRepository.save(membership);
    }

    @Override
    public List<UserMembership> findAllByUserEmailAndStatus(String email, MembershipStatus status) {
        return userMembershipRepository.findAllByUser_EmailAndStatus(email, status);
    }

    @Override
    public Optional<UserMembership> findTopByUserEmailAndStatusOrderByStartDateDesc(String email, MembershipStatus status) {
        return userMembershipRepository.findTopByUser_EmailAndStatusOrderByStartDateDesc(email, status);
    }

    @Override
    public Optional<UserMembership> findByUserEmailAndPlanIdAndStatus(String email, String planId, MembershipStatus status) {
        return userMembershipRepository.findByUser_EmailAndPlan_IdAndStatus(email, planId, status);
    }

    @Override
    public List<UserMembership> saveAll(List<UserMembership> memberships) {
        return userMembershipRepository.saveAll(memberships);
    }
}
