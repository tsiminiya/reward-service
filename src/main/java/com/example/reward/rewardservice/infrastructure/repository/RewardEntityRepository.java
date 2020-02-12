package com.example.reward.rewardservice.infrastructure.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RewardEntityRepository extends ElasticsearchRepository<RewardEntity, String> {

}
