package com.example.reward.rewardservice.infrastructure.gateway;

import com.example.reward.rewardservice.core.BonusApiGateway;
import com.example.reward.rewardservice.core.model.Bonus;
import com.example.reward.rewardservice.infrastructure.rest.BonusApiClient;

public class BonusApiGatewayImpl implements BonusApiGateway {

    private final BonusApiClient bonusApiClient;

    public BonusApiGatewayImpl(BonusApiClient bonusApiClient) {
        this.bonusApiClient = bonusApiClient;
    }

    @Override
    public void sendBonus(Bonus bonus) {
        bonusApiClient.sendBonus(bonus.getPlayerToken(), bonus.getAmount());
    }
}
