package com.example.reward.rewardservice.infrastructure.gateway;

import com.example.reward.rewardservice.core.RewardGateway;
import com.example.reward.rewardservice.core.model.Player;
import com.example.reward.rewardservice.core.model.Reward;
import com.example.reward.rewardservice.infrastructure.repository.RewardEntityRepository;

import java.util.Optional;

public class RewardGatewayImpl implements RewardGateway {

    private final RewardEntityRepository rewardEntityRepository;

    public RewardGatewayImpl(RewardEntityRepository rewardEntityRepository) {
        this.rewardEntityRepository = rewardEntityRepository;
    }

    @Override
    public Optional<Reward> getReward(Player player) {
        return rewardEntityRepository.findById(player.getPlayerToken())
                .map(rewardEntity -> new Reward(rewardEntity.getAmount()));
    }
}
