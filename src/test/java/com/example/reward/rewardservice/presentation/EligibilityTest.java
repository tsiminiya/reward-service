package com.example.reward.rewardservice.presentation;

import com.example.reward.rewardservice.RewardServiceApplication;
import com.example.reward.rewardservice.config.RewardServiceTestConfiguration;
import com.example.reward.rewardservice.core.BonusApiGateway;
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
public class EligibilityTest {

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
    public void testShouldReturnNoContentWhenPlayerIsEligible() {
        Mockito.when(rewardEntityRepository.findById(PLAYER_TOKEN))
                .thenReturn(Optional.of(new RewardEntity(PLAYER_TOKEN, BigDecimal.TEN)));

        RestAssured.given()
                    .queryParam("token", PLAYER_TOKEN)
                .when()
                    .get("/api/reward")
                .then()
                    .log().ifStatusCodeIsEqualTo(204);
    }

    @Test
    public void testShouldReturnForbiddenWhenPlayerIsNotEligible() {
        Mockito.when(rewardEntityRepository.findById(PLAYER_TOKEN))
                .thenReturn(Optional.empty());

        RestAssured.given()
                .queryParam("token", PLAYER_TOKEN)
                .when()
                .get("/api/reward")
                .then()
                .log().ifStatusCodeIsEqualTo(403);
    }
}
