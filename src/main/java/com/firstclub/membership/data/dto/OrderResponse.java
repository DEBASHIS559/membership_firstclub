package com.firstclub.membership.data.dto;


import com.firstclub.membership.data.enums.MembershipTier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String email;
    private MembershipTier tier;
    private double originalAmount;
    private int discountPercentage;
    private double finalAmount;
    private boolean freeDelivery;
    private boolean earlyAccessAllowed;
    private boolean prioritySupport;
}