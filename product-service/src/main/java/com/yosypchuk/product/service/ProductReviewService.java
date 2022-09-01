package com.yosypchuk.product.service;

import com.yosypchuk.product.model.dto.ProductRateDTO;

public interface ProductReviewService {

    void createReview(Long userId, Long productId, ProductRateDTO productRateDTO);

    void changeReview(Long userId, Long productId, ProductRateDTO productRateDTO);

    Double getAverageRate(Long productId);

}
