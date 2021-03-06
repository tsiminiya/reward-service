package com.example.reward.rewardservice.config;

import com.example.reward.rewardservice.core.BonusApiGateway;
import com.example.reward.rewardservice.core.RewardGateway;
import com.example.reward.rewardservice.core.RewardService;
import com.example.reward.rewardservice.infrastructure.gateway.BonusApiGatewayImpl;
import com.example.reward.rewardservice.infrastructure.gateway.RewardGatewayImpl;
import com.example.reward.rewardservice.infrastructure.repository.RewardEntityRepository;
import com.example.reward.rewardservice.infrastructure.rest.BonusApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("!test")
@EnableElasticsearchRepositories(basePackages = "com.example.reward.rewardservice.infrastructure.repository")
public class RewardServiceConfiguration {

    private final String bonusUrl;

    public RewardServiceConfiguration(@Value("${reward.config.bonus.url}") String bonusUrl) {
        this.bonusUrl = bonusUrl;
    }

    @Bean
    public RewardService rewardService(RewardEntityRepository rewardEntityRepository,
                                       BonusApiClient bonusApiClient) {
        return new RewardService(rewardGateway(rewardEntityRepository), bonusApiGateway(bonusApiClient));
    }

    @Bean
    public BonusApiClient bonusApiClient() {
        return new BonusApiClient(restTemplate(), bonusUrl);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RewardGateway rewardGateway(RewardEntityRepository rewardEntityRepository) {
        return new RewardGatewayImpl(rewardEntityRepository);
    }

    @Bean
    public BonusApiGateway bonusApiGateway(BonusApiClient bonusApiClient) {
        return new BonusApiGatewayImpl(bonusApiClient);
    }

}
