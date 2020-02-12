package com.example.reward.rewardservice.core;

import com.example.reward.rewardservice.core.model.Player;
import com.example.reward.rewardservice.core.model.Reward;

import java.util.Optional;

public interface RewardGateway {

    Optional<Reward> getReward(Player player);

}
