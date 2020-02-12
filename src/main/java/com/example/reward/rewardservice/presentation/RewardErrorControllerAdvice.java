package com.example.reward.rewardservice.presentation;

import com.example.reward.rewardservice.core.exception.BonusException;
import com.example.reward.rewardservice.core.model.RewardError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class RewardErrorControllerAdvice {

    private static final String BONUS_ERROR = "Bonus Error!";

    @ExceptionHandler(BonusException.class)
    public ResponseEntity<Object> handleBonusException(BonusException e) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(new RewardError(INTERNAL_SERVER_ERROR.value(), BONUS_ERROR));
    }

}
