package com.muscatlab.bob.service;

import com.muscatlab.bob.dto.recommendImageUrl.RecommendImageUrlOutput;

import java.util.List;

public interface RecommendImageService {
    List<RecommendImageUrlOutput> getAll();
}
