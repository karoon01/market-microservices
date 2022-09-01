package com.yosypchuk.product.controller;

import com.yosypchuk.product.api.ProductReviewApi;
import com.yosypchuk.product.model.dto.ProductRateDTO;
import com.yosypchuk.product.service.ProductReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductReviewController implements ProductReviewApi {

    private final ProductReviewService productRateService;

    @Override
    public ResponseEntity<Void> createProductReview(Long userId, Long productId, ProductRateDTO productRateDTO) {
        productRateService.createReview(userId, productId, productRateDTO);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> updateProductReview(Long userId, Long productId, ProductRateDTO productRateDTO) {
        productRateService.changeReview(userId, productId, productRateDTO);
        return ResponseEntity.noContent().build();
    }
}
