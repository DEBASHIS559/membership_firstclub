package com.firstclub.membership.data.dto;

import com.firstclub.membership.data.enums.MembershipTier;
import com.firstclub.membership.data.enums.PlanType;
import lombok.Data;

@Data
public class MembershipPlanResponse {
    private String id;
    private PlanType planType;
    private Double price;
    private MembershipTier tier;
    private String description;
    private Integer durationInDays;
}