package com.firstclub.membership.dao;

import com.firstclub.membership.data.entity.UserMembership;
import com.firstclub.membership.data.enums.MembershipStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMembershipDao {
    UserMembership save(UserMembership membership);
    List<UserMembership> findAllByUserEmailAndStatus(String email, MembershipStatus status);
    Optional<UserMembership> findTopByUserEmailAndStatusOrderByStartDateDesc(String email, MembershipStatus status);
    Optional<UserMembership> findByUserEmailAndPlanIdAndStatus(String email, String planId, MembershipStatus status);
    List<UserMembership> saveAll(List<UserMembership> memberships);
}