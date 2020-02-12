package com.example.reward.rewardservice.core;

import com.example.reward.rewardservice.core.model.Bonus;
import com.example.reward.rewardservice.core.model.Player;

import java.math.BigDecimal;

public class RewardService {

    private final RewardGateway rewardGateway;
    private final BonusApiGateway bonusApiGateway;

    public RewardService(RewardGateway rewardGateway,
                         BonusApiGateway bonusApiGateway) {
        this.rewardGateway = rewardGateway;
        this.bonusApiGateway = bonusApiGateway;
    }

    public void rewardPlayer(Player player) {
        Bonus bonus = rewardGateway.getReward(player)
                .map(reward -> createBonus(player.getPlayerToken(), reward.getAmount()))
                .orElseGet(() -> createBonus(player.getPlayerToken(), BigDecimal.ZERO));
        bonusApiGateway.sendBonus(bonus);
    }

    private Bonus createBonus(String playerToken,
                              BigDecimal amount) {
        return new Bonus(playerToken, amount);
    }

}
