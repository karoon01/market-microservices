package com.yosypchuk.product.test.util;

import com.yosypchuk.product.model.ProductCategory;
import com.yosypchuk.product.model.dto.ProductCategoryDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestCategoryDataUtil {
    public static Long MOCK_ID = 1L;
    public static String MOCK_NAME = "Category name";

    public static ProductCategory createCategory() {
        return ProductCategory.builder()
                .id(MOCK_ID)
                .name(MOCK_NAME)
                .build();
    }

    public static ProductCategoryDTO createCategoryDto() {
        return ProductCategoryDTO.builder()
                .id(MOCK_ID)
                .name(MOCK_NAME)
                .build();
    }
}
