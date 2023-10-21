package com.muscatlab.bob.repository.impl;

import com.muscatlab.bob.domain.recommendImageUrl.entity.RecommendImageUrl;
import com.muscatlab.bob.repository.RecommendImageUrlRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.muscatlab.bob.domain.recommendImageUrl.entity.QRecommendImageUrl.recommendImageUrl;


@RequiredArgsConstructor
public class RecommendImageUrlRepositoryCustomImpl implements RecommendImageUrlRepositoryCustom {
    private final JPAQueryFactory query;


    @Override
    public List<RecommendImageUrl> findAll() {
        return query.selectFrom(recommendImageUrl).fetch();
    }
}
