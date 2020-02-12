package com.example.reward.rewardservice.infrastructure.rest;

import com.example.reward.rewardservice.core.exception.BonusException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class BonusApiClient {

    private final RestTemplate restTemplate;
    private final String bonusUrl;

    public BonusApiClient(RestTemplate restTemplate, String bonusUrl) {
        this.restTemplate = restTemplate;
        this.bonusUrl = bonusUrl;
    }

    public void sendBonus(String playerToken, BigDecimal amount) {
        HttpEntity<BonusRequest> request = new HttpEntity<>(new BonusRequest(playerToken, amount));
        try {
            restTemplate.exchange(bonusUrl, HttpMethod.POST, request, Object.class);
        } catch (RestClientException e) {
            throw new BonusException();
        }
    }

    private static class BonusRequest {

        private String playerToken;
        private BigDecimal amount;

        public BonusRequest() {
        }

        public BonusRequest(String playerToken, BigDecimal amount) {
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

}
