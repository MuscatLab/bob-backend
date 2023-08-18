package com.muscatlab.bob.controller;

import com.muscatlab.bob.dto.customMenu.CustomMenuOutput;
import com.muscatlab.bob.dto.recommendImageUrl.RecommendImageUrlOutput;
import com.muscatlab.bob.service.OrderHistoryService;
import com.muscatlab.bob.service.RecommendImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "추천 메뉴 이미지", description = "추천 메뉴 이미지 관련 API")
@RestController
@RequestMapping("/recommends")
@RequiredArgsConstructor
public class RecommendImageUrlController {
    private final RecommendImageService recommendImageService;
    private final OrderHistoryService orderHistoryService;

    @Operation(summary = "추천 메뉴 이미지 전체 조회")
    @GetMapping("/images")
    @ResponseStatus(HttpStatus.OK)
    public List<RecommendImageUrlOutput> getAll() {
        return this.recommendImageService.getAll();
    }

    @Operation(summary = "주문 내역 전체 조회")
    @GetMapping("/order/{cardUid}")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomMenuOutput> getAll(
            @PathVariable(value = "cardUid") String cardUid
    ) {
        return this.orderHistoryService.getAllByCardUid(cardUid);
    }
}
