package com.firstclub.membership.data.dto;

import com.firstclub.membership.data.enums.MembershipStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserMembershipResponse {
    private String id;
    private UserResponse user;
    private MembershipPlanResponse plan;
    private LocalDate startDate;
    private LocalDate expiryDate;
    private MembershipStatus status;
}
