package com.example.reward.rewardservice.core;

import com.example.reward.rewardservice.core.model.Bonus;

public interface BonusApiGateway {

    void sendBonus(Bonus bonus);

}
