package com.firstclub.membership.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.firstclub.membership.data.enums.MembershipStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "user_memberships")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserMembership {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private MembershipPlan plan;

    private LocalDate startDate;
    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    private MembershipStatus status;
}

