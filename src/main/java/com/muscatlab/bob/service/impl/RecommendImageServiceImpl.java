package com.muscatlab.bob.service.impl;

import com.muscatlab.bob.dto.recommendImageUrl.RecommendImageUrlOutput;
import com.muscatlab.bob.repository.RecommendImageUrlRepository;
import com.muscatlab.bob.service.RecommendImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendImageServiceImpl implements RecommendImageService {
    private final RecommendImageUrlRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<RecommendImageUrlOutput> getAll() {
        return repository.findAll().stream()
                .map(RecommendImageUrlOutput::from)
                .toList();
    }
}
