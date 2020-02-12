package com.example.reward.rewardservice.core.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Bonus {

    private final String playerToken;
    private final BigDecimal amount;

    public Bonus(String playerToken, BigDecimal amount) {
        this.playerToken = playerToken;
        this.amount = amount;
    }

    public String getPlayerToken() {
        return playerToken;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bonus bonus = (Bonus) o;
        return Objects.equals(playerToken, bonus.playerToken) &&
                Objects.equals(amount, bonus.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerToken, amount);
    }
}
