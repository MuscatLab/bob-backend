package com.muscatlab.bob.repository;

import com.muscatlab.bob.domain.recommendImageUrl.entity.RecommendImageUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecommendImageUrlRepository extends JpaRepository<RecommendImageUrl, UUID>, RecommendImageUrlRepositoryCustom {
}
