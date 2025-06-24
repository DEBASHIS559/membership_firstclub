package com.firstclub.membership.service.impl;

import com.firstclub.membership.data.dto.OrderRequest;
import com.firstclub.membership.data.dto.OrderResponse;
import com.firstclub.membership.data.dto.UserMembershipResponse;
import com.firstclub.membership.data.enums.MembershipTier;
import com.firstclub.membership.service.MembershipService;
import com.firstclub.membership.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final MembershipService membershipService;

    @Override
    public ResponseEntity<OrderResponse>  processOrder(OrderRequest request) {
        UserMembershipResponse membershipOpt = membershipService.getCurrentMembership(request.getEmail()).getBody();


        MembershipTier tier = membershipOpt.getPlan().getTier();
        double originalAmount = request.getOriginalAmount();
        int discount = tier.getDiscountPercentage();
        double finalAmount = originalAmount - (originalAmount * discount / 100.0);

        boolean earlyAccessAllowed =  tier.isEarlyAccess();
        if (!earlyAccessAllowed) {
            throw new IllegalStateException("Early access not allowed for this tier");
        }
        log.info(String.valueOf(finalAmount));
        return ResponseEntity.ok(new OrderResponse(
                request.getEmail(),
                tier,
                originalAmount,
                discount,
                finalAmount,
                tier.isFreeDelivery(),
                tier.isEarlyAccess(),
                tier.isPrioritySupport()
        ));
    }
}