package com.muscatlab.bob.repository;

import com.muscatlab.bob.domain.recommendImageUrl.entity.RecommendImageUrl;

import java.util.List;

public interface RecommendImageUrlRepositoryCustom {
    List<RecommendImageUrl> findAll();
}
