package com.muscatlab.bob.domain.recommendImageUrl.query.impl;

import com.muscatlab.bob.domain.recommendImageUrl.entity.RecommendImageUrl;
import com.muscatlab.bob.domain.recommendImageUrl.query.RecommendImageUrlQueryService;
import com.muscatlab.bob.repository.RecommendImageUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecommendImageUrlQueryServiceImpl implements RecommendImageUrlQueryService {
    private final RecommendImageUrlRepository recommendImageUrlRepository;

    @Override
    public List<RecommendImageUrl> getAll() {
        return this.recommendImageUrlRepository.findAll();
    }
}
