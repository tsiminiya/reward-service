package com.example.reward.rewardservice.infrastructure.rest;

import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class FakeBonusApiClient extends BonusApiClient {

    public FakeBonusApiClient(RestTemplate restTemplate, String bonusUrl) {
        super(restTemplate, bonusUrl);
    }

    @Override
    public void sendBonus(String playerToken, BigDecimal amount) {
        // Do Nothing
    }

}
