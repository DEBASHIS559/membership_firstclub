package com.firstclub.membership.data.enums;

public enum MembershipTier {
    SILVER(1, 0, false, false, false),
    GOLD(2, 5, true, true, false),
    PLATINUM(3, 10, true, true, true);

    private final int level;
    private final int discountPercentage;
    private final boolean freeDelivery;
    private final boolean earlyAccess;
    private final boolean prioritySupport;

    MembershipTier(int level, int discountPercentage, boolean freeDelivery, boolean earlyAccess, boolean prioritySupport) {
        this.level = level;
        this.discountPercentage = discountPercentage;
        this.freeDelivery = freeDelivery;
        this.earlyAccess = earlyAccess;
        this.prioritySupport = prioritySupport;
    }

    public int getLevel() {
        return level;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public boolean isFreeDelivery() {
        return freeDelivery;
    }

    public boolean isEarlyAccess() {
        return earlyAccess;
    }

    public boolean isPrioritySupport() {
        return prioritySupport;
    }
}


