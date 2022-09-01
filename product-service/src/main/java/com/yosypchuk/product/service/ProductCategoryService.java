package com.yosypchuk.product.service;

import com.yosypchuk.product.model.dto.ProductCategoryDTO;

import java.util.List;

public interface ProductCategoryService {
    ProductCategoryDTO createProductCategory(ProductCategoryDTO productCategoryDTO);

    List<ProductCategoryDTO> getAllProductCategories();

    void removeProductCategory(Long id);

    void addCategoryToProduct(Long productId, Long categoryId);

    void removeCategoryFromProduct(Long productId, Long categoryId);
}
