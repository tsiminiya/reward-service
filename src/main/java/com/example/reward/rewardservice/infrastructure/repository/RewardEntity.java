package com.example.reward.rewardservice.infrastructure.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Document(indexName = "rewards")
public class RewardEntity {

    @Id
    private String playerToken;
    private BigDecimal amount;

    public RewardEntity() {
    }

    public RewardEntity(String playerToken, BigDecimal amount) {
        this.playerToken = playerToken;
        this.amount = amount;
    }

    public String getPlayerToken() {
        return playerToken;
    }

    public void setPlayerToken(String playerToken) {
        this.playerToken = playerToken;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
