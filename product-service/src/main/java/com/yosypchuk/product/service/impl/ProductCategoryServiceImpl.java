package com.yosypchuk.product.service.impl;

import com.yosypchuk.product.exception.EntityAlreadyExistException;
import com.yosypchuk.product.exception.EntityNotFoundException;
import com.yosypchuk.product.mapper.ProductCategoryMapper;
import com.yosypchuk.product.mapper.ProductMapper;
import com.yosypchuk.product.model.ProductCategory;
import com.yosypchuk.product.model.dto.ProductCategoryDTO;
import com.yosypchuk.product.model.dto.ProductDTO;
import com.yosypchuk.product.repository.ProductCategoryRepository;
import com.yosypchuk.product.repository.ProductRepository;
import com.yosypchuk.product.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public ProductCategoryDTO createProductCategory(ProductCategoryDTO productCategoryDTO) {
        String name = productCategoryDTO.getName();
        Optional<ProductCategory> possibleCategory = productCategoryRepository.findProductCategoryByName(name);

        if(possibleCategory.isPresent()) {
            log.error("Product category with name: {} is already exist!", name);
            throw new EntityAlreadyExistException("Product category is already exist!");
        }

        ProductCategory productCategory = ProductCategoryMapper.INSTANCE.mapProductCategory(productCategoryDTO);
        log.info("Create product category with name: {}", name);
        productCategoryRepository.save(productCategory);

        return productCategoryDTO;
    }

    @Override
    public List<ProductCategoryDTO> getAllProductCategories() {
        log.info("Get all product categories");
        return productCategoryRepository.findAll()
                .stream()
                .map(ProductCategoryMapper.INSTANCE::mapProductCategoryDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void removeProductCategory(Long id) {
        log.info("Trying to get product category with id: {}", id);
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product category doesn't exist!"));

        log.info("Delete category with id: {}", id);
        productCategoryRepository.delete(category);
    }

    @Transactional
    @Override
    public void addCategoryToProduct(Long productId, Long categoryId) {
        log.info("Trying to get product by id: {}", productId);
        productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product doesn't exist!"));

        log.info("Trying to get product category by id: {}", categoryId);
        productCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Product category doesn't exist!"));

        log.info("Add product category with id: {} to product with id: {}", productId, categoryId);
        productCategoryRepository.addProductCategory(productId, categoryId);
    }

    @Transactional
    @Override
    public void removeCategoryFromProduct(Long productId, Long categoryId) {
        log.info("Trying to get product by id: {}", productId);
        productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product doesn't exist!"));

        log.info("Trying to get product category by id: {}", categoryId);
        productCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Product category doesn't exist!"));

        log.info("Remove product category with id: {} from product with id: {}", productId, categoryId);
        productCategoryRepository.removeProductCategory(productId, categoryId);
    }

    @Override
    public List<ProductDTO> getAllProductByCategoryId(Long productCategoryId) {
        log.info("Get product category with id: {}", productCategoryId);
        productCategoryRepository.findById(productCategoryId)
                .orElseThrow(() -> new EntityNotFoundException("Product category doesn't exist!"));

        log.info("Get all ");
        return productCategoryRepository.findAllProductsByCategory(productCategoryId)
                .stream()
                .map(ProductMapper.INSTANCE::mapProductDto)
                .collect(Collectors.toList());
    }
}
