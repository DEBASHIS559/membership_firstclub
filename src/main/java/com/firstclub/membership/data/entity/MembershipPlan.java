package com.firstclub.membership.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.firstclub.membership.data.enums.MembershipTier;
import com.firstclub.membership.data.enums.PlanType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "membership_plans")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class MembershipPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private PlanType planType;

    private Double price;

    @Enumerated(EnumType.STRING)
    private MembershipTier tier;

    private String description;
    private Integer durationInDays;
}
