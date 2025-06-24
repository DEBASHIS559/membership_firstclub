package com.firstclub.membership.service;

import com.firstclub.membership.data.dto.MembershipPlanRequest;
import com.firstclub.membership.data.dto.MembershipPlanResponse;
import com.firstclub.membership.data.dto.UserMembershipResponse;
import com.firstclub.membership.data.entity.MembershipPlan;
import com.firstclub.membership.data.entity.UserMembership;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface MembershipService {
    ResponseEntity<MembershipPlanResponse>  createPlan(MembershipPlanRequest plan);
    ResponseEntity<List<MembershipPlanResponse>> getAllPlans();
    ResponseEntity<UserMembershipResponse> subscribe(String email, String planId);
    ResponseEntity<UserMembershipResponse> upgrade(String email, String newPlanId);
    ResponseEntity<UserMembershipResponse> downgrade(String email, String newPlanId);
    ResponseEntity<String> cancel(String email);
    ResponseEntity<String>  cancelAll(String email);
    ResponseEntity<String>  cancelByPlanId(String email, String planId);
    ResponseEntity<UserMembershipResponse> getCurrentMembership(String email);
}