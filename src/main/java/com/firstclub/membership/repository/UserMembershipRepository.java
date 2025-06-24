package com.firstclub.membership.repository;

import com.firstclub.membership.data.entity.UserMembership;
import com.firstclub.membership.data.enums.MembershipStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserMembershipRepository extends JpaRepository<UserMembership, String> {
    Optional<UserMembership> findTopByUser_EmailAndStatusOrderByStartDateDesc(String email, MembershipStatus status);
    List<UserMembership> findAllByUser_EmailAndStatus(String email, MembershipStatus status);
    Optional<UserMembership> findByUser_EmailAndPlan_IdAndStatus(String email, String planId, MembershipStatus status);
}

