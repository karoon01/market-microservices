package com.yosypchuk.product.controller;

import com.yosypchuk.product.api.ProductCategoryApi;
import com.yosypchuk.product.model.dto.ProductCategoryDTO;
import com.yosypchuk.product.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductCategoryController implements ProductCategoryApi {

    private final ProductCategoryService productCategoryService;

    @Override
    public ProductCategoryDTO createProductCategory(ProductCategoryDTO productCategoryDTO) {
        return productCategoryService.createProductCategory(productCategoryDTO);
    }

    @Override
    public List<ProductCategoryDTO> getAllProductCategories() {
        return productCategoryService.getAllProductCategories();
    }

    @Override
    public ResponseEntity<Void> removeProductCategory(Long id) {
        productCategoryService.removeProductCategory(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> addCategoryToProduct(Long productId, Long categoryId) {
        productCategoryService.addCategoryToProduct(productId, categoryId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> removeCategoryFromProduct(Long productId, Long categoryId) {
        productCategoryService.removeCategoryFromProduct(productId, categoryId);
        return ResponseEntity.noContent().build();
    }
}
