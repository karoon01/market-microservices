package com.yosypchuk.product.test.util;

import com.yosypchuk.product.model.Product;
import com.yosypchuk.product.model.ProductCategory;
import com.yosypchuk.product.model.ProductReview;
import com.yosypchuk.product.model.dto.ProductDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestProductDataUtil {
    public static Long MOCK_ID = 1L;
    public static String MOCK_NAME = "Product name";
    public static String MOCK_DESCRIPTION = "Product description";
    public static ProductCategory MOCK_CATEGORY = TestCategoryDataUtil.createCategory();
    public static ProductReview MOCK_RATE = TestReviewDataUtil.createRate();
    public static Double MOCK_PRICE = 0.0;
    public static Integer MOCK_AMOUNT = 0;

    public static Long MOCK_ID_SECOND = 2L;
    public static String MOCK_NAME_SECOND = "Second product name";
    public static String MOCK_DESCRIPTION_SECOND = "Second product description";
    public static Integer MOCK_AMOUNT_UPDATED = 5;

    public static Product createProduct() {
        return Product.builder()
                .id(MOCK_ID)
                .name(MOCK_NAME)
                .description(MOCK_DESCRIPTION)
                .categories(List.of(MOCK_CATEGORY))
                .price(MOCK_PRICE)
                .amount(MOCK_AMOUNT)
                .build();
    }

    public static ProductDTO createProductDto() {
        return ProductDTO.builder()
                .id(MOCK_ID)
                .name(MOCK_NAME)
                .description(MOCK_DESCRIPTION)
                .categories(List.of(MOCK_CATEGORY))
                .price(MOCK_PRICE)
                .amount(MOCK_AMOUNT)
                .build();
    }

    public static Product createUpdateProduct() {
        return Product.builder()
                .id(MOCK_ID)
                .name(MOCK_NAME_SECOND)
                .description(MOCK_DESCRIPTION_SECOND)
                .categories(List.of(MOCK_CATEGORY))
                .price(MOCK_PRICE)
                .amount(MOCK_AMOUNT_UPDATED)
                .build();
    }

    public static ProductDTO createUpdateProductDto() {
        return ProductDTO.builder()
                .id(MOCK_ID)
                .name(MOCK_NAME_SECOND)
                .description(MOCK_DESCRIPTION_SECOND)
                .categories(List.of(MOCK_CATEGORY))
                .price(MOCK_PRICE)
                .amount(MOCK_AMOUNT_UPDATED)
                .build();
    }

    public static Product createSecondProduct() {
        return Product.builder()
                .id(MOCK_ID_SECOND)
                .name(MOCK_NAME_SECOND)
                .description(MOCK_DESCRIPTION_SECOND)
                .categories(List.of(MOCK_CATEGORY))
                .price(MOCK_PRICE)
                .amount(MOCK_AMOUNT)
                .build();
    }

    public static ProductDTO createSecondProductDto() {
        return ProductDTO.builder()
                .id(MOCK_ID_SECOND)
                .name(MOCK_NAME_SECOND)
                .description(MOCK_DESCRIPTION_SECOND)
                .categories(List.of(MOCK_CATEGORY))
                .price(MOCK_PRICE)
                .amount(MOCK_AMOUNT)
                .build();
    }
}
