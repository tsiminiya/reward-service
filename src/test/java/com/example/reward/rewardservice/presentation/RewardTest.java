package com.example.reward.rewardservice.presentation;

import com.example.reward.rewardservice.RewardServiceApplication;
import com.example.reward.rewardservice.config.RewardServiceTestConfiguration;
import com.example.reward.rewardservice.core.BonusApiGateway;
import com.example.reward.rewardservice.core.model.Bonus;
import com.example.reward.rewardservice.infrastructure.repository.RewardEntity;
import com.example.reward.rewardservice.infrastructure.repository.RewardEntityRepository;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.mapper.factory.DefaultJackson1ObjectMapperFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {
        RewardServiceApplication.class,
        RewardServiceTestConfiguration.class
})
public class RewardTest {

    public static final String PLAYER_TOKEN = "abc123";

    @LocalServerPort
    private Integer port;

    @MockBean
    private RewardEntityRepository rewardEntityRepository;
    @MockBean
    private BonusApiGateway bonusApiGateway;

    @BeforeEach
    public void initialize() {
        RestAssured.port = port;

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();

        RestAssured.config = RestAssured.config()
                .objectMapperConfig(new ObjectMapperConfig()
                        .jackson1ObjectMapperFactory(new DefaultJackson1ObjectMapperFactory()));
    }

    @Test
    public void testShouldAbleToSendBonusWhenRewardIsAvailable() {
        Mockito.when(rewardEntityRepository.findById(PLAYER_TOKEN))
                .thenReturn(Optional.of(new RewardEntity(PLAYER_TOKEN, BigDecimal.TEN)));

        RestAssured.given()
                .body(new PlayerRequest(PLAYER_TOKEN))
            .when()
                .post("/api/reward")
            .then()
                .log()
                    .ifStatusCodeIsEqualTo(200)
                    .statusCode(200);

        Mockito.verify(bonusApiGateway).sendBonus(new Bonus(PLAYER_TOKEN, BigDecimal.TEN));
    }

    @Test
    public void testShouldAbleToSendZeroBonusWhenRewardIsAvailable() {
        Mockito.when(rewardEntityRepository.findById(PLAYER_TOKEN)).thenReturn(Optional.empty());

        RestAssured.given()
                .body(new PlayerRequest(PLAYER_TOKEN))
            .when()
                .post("/api/reward")
            .then()
                .log()
                    .ifValidationFails()
                    .statusCode(200);

        Mockito.verify(bonusApiGateway).sendBonus(new Bonus(PLAYER_TOKEN, BigDecimal.ZERO));
    }

    private static class PlayerRequest {

        private String playerToken;

        public PlayerRequest() {
        }

        public PlayerRequest(String playerToken) {
            this.playerToken = playerToken;
        }

        public String getPlayerToken() {
            return playerToken;
        }

        public void setPlayerToken(String playerToken) {
            this.playerToken = playerToken;
        }
    }

}
