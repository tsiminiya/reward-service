package com.example.reward.rewardservice.presentation;

import com.example.reward.rewardservice.core.RewardService;
import com.example.reward.rewardservice.core.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reward")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping
    public ResponseEntity<?> isPlayerEligible(@RequestParam("token") String playerToken) {
        if (rewardService.isPlayerEligible(new Player(playerToken))) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping
    public ResponseEntity<?> rewardPlayer(@RequestBody Player player) {
        rewardService.rewardPlayer(player);
        return ResponseEntity.ok().build();
    }
}
