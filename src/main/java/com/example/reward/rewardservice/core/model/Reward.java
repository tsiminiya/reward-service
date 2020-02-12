package com.example.reward.rewardservice.core.model;

import java.math.BigDecimal;

public class Reward {

    private final BigDecimal amount;

    public Reward(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
