package com.firstclub.membership.service.impl;

import com.firstclub.membership.dao.MembershipPlanDao;
import com.firstclub.membership.dao.UserDao;
import com.firstclub.membership.dao.UserMembershipDao;
import com.firstclub.membership.data.dto.MembershipPlanRequest;
import com.firstclub.membership.data.dto.MembershipPlanResponse;
import com.firstclub.membership.data.dto.UserMembershipResponse;
import com.firstclub.membership.data.entity.MembershipPlan;
import com.firstclub.membership.data.entity.User;
import com.firstclub.membership.data.entity.UserMembership;
import com.firstclub.membership.data.enums.MembershipStatus;
import com.firstclub.membership.data.enums.MembershipTier;
import com.firstclub.membership.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {
    private final MembershipPlanDao planDao;
    private final UserMembershipDao membershipDao;
    private final UserDao userDao;
    private final ModelMapper mapper;

    public ResponseEntity<MembershipPlanResponse> createPlan(MembershipPlanRequest req) {
        MembershipPlan plan = mapper.map(req, MembershipPlan.class);
        return ResponseEntity.ok(mapper.map(planDao.save(plan), MembershipPlanResponse.class));
    }

    public ResponseEntity<List<MembershipPlanResponse>> getAllPlans() {
        return ResponseEntity.ok(planDao.findAll().stream()
                .map(p -> mapper.map(p, MembershipPlanResponse.class))
                .toList());
    }

    public ResponseEntity<UserMembershipResponse> subscribe(String email, String planId) {
        User user = userDao.findByEmail(email).orElseThrow();
        MembershipPlan plan = planDao.findById(planId).orElseThrow();

        UserMembership m = new UserMembership();
        m.setUser(user);
        m.setPlan(plan);
        m.setStartDate(LocalDate.now());
        m.setExpiryDate(LocalDate.now().plusDays(plan.getDurationInDays()));
        m.setStatus(MembershipStatus.ACTIVE);

        return ResponseEntity.ok(mapper.map(membershipDao.save(m), UserMembershipResponse.class));
    }

    @Override
    public ResponseEntity<UserMembershipResponse> upgrade(String email, String newPlanId) {
        MembershipPlan newPlan = planDao.findById(newPlanId).orElseThrow(() -> new RuntimeException("Plan not found"));
        Optional<UserMembership> currentOpt = membershipDao.findTopByUserEmailAndStatusOrderByStartDateDesc(email, MembershipStatus.ACTIVE);
        if (currentOpt.isPresent()) {
            MembershipTier currentTier = currentOpt.get().getPlan().getTier();
            if (newPlan.getTier().getLevel() <= currentTier.getLevel()) {
                throw new RuntimeException("Cannot upgrade to same or lower tier");
            }
            cancelByPlanId(email, currentOpt.get().getPlan().getId());
        }

        return subscribe(email, newPlanId);
    }

    @Override
    public ResponseEntity<UserMembershipResponse> downgrade(String email, String newPlanId) {
        MembershipPlan newPlan = planDao.findById(newPlanId).orElseThrow(() -> new RuntimeException("Plan not found"));
        Optional<UserMembership> currentOpt = membershipDao.findTopByUserEmailAndStatusOrderByStartDateDesc(email, MembershipStatus.ACTIVE);
        if (currentOpt.isPresent()) {
            MembershipTier currentTier = currentOpt.get().getPlan().getTier();
            if (newPlan.getTier().getLevel() >= currentTier.getLevel()) {
                throw new RuntimeException("Cannot downgrade to same or higher tier");
            }
            cancelByPlanId(email, currentOpt.get().getPlan().getId());
        }
        return subscribe(email, newPlanId);
    }

    public ResponseEntity<String> cancel(String email) {
        membershipDao.findTopByUserEmailAndStatusOrderByStartDateDesc(email, MembershipStatus.ACTIVE)
                .ifPresent(m -> {
                    m.setStatus(MembershipStatus.CANCELLED);
                    membershipDao.save(m);
                });
        return ResponseEntity.ok("Cancelled successfully");
    }

    public ResponseEntity<UserMembershipResponse> getCurrentMembership(String email) {
        return ResponseEntity.ok(membershipDao.findTopByUserEmailAndStatusOrderByStartDateDesc(email, MembershipStatus.ACTIVE)
                .map(m -> mapper.map(m, UserMembershipResponse.class)).orElseThrow(()-> new NoSuchElementException()));
    }

    public ResponseEntity<String> cancelAll(String email) {
        List<UserMembership> memberships = membershipDao.findAllByUserEmailAndStatus(email, MembershipStatus.ACTIVE);
        memberships.forEach(m -> m.setStatus(MembershipStatus.CANCELLED));
        membershipDao.saveAll(memberships);
        return ResponseEntity.ok("Cancelled successfully");
    }

    public ResponseEntity<String> cancelByPlanId(String email, String planId) {
        membershipDao.findByUserEmailAndPlanIdAndStatus(email, planId, MembershipStatus.ACTIVE)
                .ifPresent(m -> {
                    m.setStatus(MembershipStatus.CANCELLED);
                    membershipDao.save(m);
                });
        return ResponseEntity.ok("Cancelled successfully");
    }

}