package com.muscatlab.bob.domain.recommendImageUrl.query;

import com.muscatlab.bob.domain.recommendImageUrl.entity.RecommendImageUrl;

import java.util.List;

public interface RecommendImageUrlQueryService {
    List<RecommendImageUrl> getAll();
}
