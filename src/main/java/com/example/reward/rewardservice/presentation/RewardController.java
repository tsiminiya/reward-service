package com.example.reward.rewardservice.presentation;

import com.example.reward.rewardservice.core.RewardService;
import com.example.reward.rewardservice.core.model.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reward")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @PostMapping
    public ResponseEntity<?> rewardPlayer(@RequestBody Player player) {
        rewardService.rewardPlayer(player);
        return ResponseEntity.ok().build();
    }
}
