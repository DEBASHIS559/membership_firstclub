package com.firstclub.membership.controller;


import com.firstclub.membership.data.dto.MembershipPlanRequest;
import com.firstclub.membership.data.dto.MembershipPlanResponse;
import com.firstclub.membership.data.dto.UserMembershipResponse;
import com.firstclub.membership.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/membership")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService service;

    @PostMapping("/plan")
    public ResponseEntity<MembershipPlanResponse> createPlan(@RequestBody MembershipPlanRequest plan) {
        return service.createPlan(plan);
    }

    @GetMapping("/plans")
    public ResponseEntity<List<MembershipPlanResponse>> getAllPlans() {
        return service.getAllPlans();
    }

    @PostMapping("/subscribe")
    public ResponseEntity<UserMembershipResponse> subscribe(@RequestParam String email, @RequestParam String planId) {
        return service.subscribe(email, planId);
    }

    @PatchMapping("/upgrade")
    public ResponseEntity<UserMembershipResponse> upgrade(@RequestParam String email, @RequestParam String newPlanId) {
        return service.upgrade(email, newPlanId);
    }

    @PatchMapping("/downgrade")
    public ResponseEntity<UserMembershipResponse> downgrade(@RequestParam String email, @RequestParam String newPlanId) {
        return service.downgrade(email, newPlanId);
    }
// cancel last membership record
    @PostMapping("/cancel")
    public ResponseEntity<String> cancel(@RequestParam String email) {
       return service.cancel(email);

    }

    @PostMapping("/cancelAll")
    public ResponseEntity<String> cancelAll(@RequestParam String email) {
        return service.cancelAll(email);
    }

    @PostMapping("/cancelByPlan")
    public ResponseEntity<String> cancelByPlanId(@RequestParam String email, @RequestParam String planId) {
       return service.cancelByPlanId(email, planId);

    }
// last record/membership
    @GetMapping("/current")
    public ResponseEntity<UserMembershipResponse> current(@RequestParam String email) {
        return service.getCurrentMembership(email);
    }
}
