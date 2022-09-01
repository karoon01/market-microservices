package com.yosypchuk.product.test.util;

import com.yosypchuk.product.model.Product;
import com.yosypchuk.product.model.ProductReview;
import com.yosypchuk.product.model.dto.ProductRateDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestReviewDataUtil {
    public static Long MOCK_ID = 1L;
    public static  Product MOCK_PRODUCT = TestProductDataUtil.createProduct();
    public static Long MOCK_USER_ID = 1L;
    public static Integer MOCK_RATE = 4;
    public static String MOCK_COMMENT = "Comment bla-bla-bla";
    public static String MOCK_PROS = "Pros for product";
    public static String MOCK_CONS = "Cons for product";

    public static Integer MOCK_UPDATED_RATE = 3;
    public static String MOCK_UPDATED_COMMENT = "Updated comment bla-bla-bla";
    public static String MOCK_UPDATED_PROS = "Updated pros for product";
    public static String MOCK_UPDATED_CONS = "Updated cons for product";

    public static ProductReview createRate() {
        return ProductReview.builder()
                .id(MOCK_ID)
                .product(MOCK_PRODUCT)
                .userId(MOCK_USER_ID)
                .rate(MOCK_RATE)
                .comment(MOCK_COMMENT)
                .pros(MOCK_PROS)
                .cons(MOCK_CONS)
                .build();
    }

    public static ProductRateDTO createRateDTO() {
        return ProductRateDTO.builder()
                .userId(MOCK_USER_ID)
                .rate(MOCK_RATE)
                .comment(MOCK_COMMENT)
                .pros(MOCK_PROS)
                .cons(MOCK_CONS)
                .build();
    }

    public static ProductReview createUpdatedRate() {
        return ProductReview.builder()
                .id(MOCK_ID)
                .product(MOCK_PRODUCT)
                .userId(MOCK_USER_ID)
                .rate(MOCK_UPDATED_RATE)
                .comment(MOCK_UPDATED_COMMENT)
                .pros(MOCK_UPDATED_PROS)
                .cons(MOCK_UPDATED_CONS)
                .build();
    }

    public static ProductRateDTO createUpdatedRateDTO() {
        return ProductRateDTO.builder()
                .userId(MOCK_USER_ID)
                .rate(MOCK_UPDATED_RATE)
                .comment(MOCK_UPDATED_COMMENT)
                .pros(MOCK_UPDATED_PROS)
                .cons(MOCK_UPDATED_CONS)
                .build();
    }
}
