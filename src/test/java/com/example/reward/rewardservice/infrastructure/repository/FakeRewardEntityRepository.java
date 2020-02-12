package com.example.reward.rewardservice.infrastructure.repository;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.math.BigDecimal;
import java.util.Optional;

public class FakeRewardEntityRepository implements RewardEntityRepository {

    @Override
    public Optional<RewardEntity> findById(String s) {
        RewardEntity rewardEntity = new RewardEntity();
        rewardEntity.setAmount(new BigDecimal("100.00"));
        rewardEntity.setPlayerToken("player123");
        return Optional.of(rewardEntity);
    }

    @Override
    public <S extends RewardEntity> S index(S s) {
        return null;
    }

    @Override
    public <S extends RewardEntity> S indexWithoutRefresh(S s) {
        return null;
    }

    @Override
    public Iterable<RewardEntity> search(QueryBuilder queryBuilder) {
        return null;
    }

    @Override
    public Page<RewardEntity> search(QueryBuilder queryBuilder, Pageable pageable) {
        return null;
    }

    @Override
    public Page<RewardEntity> search(SearchQuery searchQuery) {
        return null;
    }

    @Override
    public Page<RewardEntity> searchSimilar(RewardEntity rewardEntity, String[] strings, Pageable pageable) {
        return null;
    }

    @Override
    public void refresh() {

    }

    @Override
    public Class<RewardEntity> getEntityClass() {
        return null;
    }

    @Override
    public Iterable<RewardEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<RewardEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends RewardEntity> S save(S s) {
        return null;
    }

    @Override
    public <S extends RewardEntity> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<RewardEntity> findAll() {
        return null;
    }

    @Override
    public Iterable<RewardEntity> findAllById(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(RewardEntity rewardEntity) {

    }

    @Override
    public void deleteAll(Iterable<? extends RewardEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
