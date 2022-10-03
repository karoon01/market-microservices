package com.yosypchuk.product.service;

import com.yosypchuk.product.model.dto.ProductCategoryDTO;
import com.yosypchuk.product.model.dto.ProductDTO;

import java.util.List;

public interface ProductCategoryService {
    ProductCategoryDTO createProductCategory(ProductCategoryDTO productCategoryDTO);

    List<ProductCategoryDTO> getAllProductCategories();

    void removeProductCategory(Long id);

    void addCategoryToProduct(Long productId, Long categoryId);

    void removeCategoryFromProduct(Long productId, Long categoryId);

    List<ProductDTO> getAllProductByCategoryId(Long productCategoryId);
}
