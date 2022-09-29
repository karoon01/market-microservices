package com.yosypchuk.product.service.impl;

import com.yosypchuk.product.exception.EntityNotFoundException;
import com.yosypchuk.product.model.Product;
import com.yosypchuk.product.model.ProductReview;
import com.yosypchuk.product.model.dto.ProductRateDTO;
import com.yosypchuk.product.repository.ProductRateRepository;
import com.yosypchuk.product.repository.ProductRepository;
import com.yosypchuk.product.service.ProductReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductReviewServiceImpl implements ProductReviewService {

    private final ProductRateRepository productRateRepository;
    private final ProductRepository productRepository;

    @Override
    public void createReview(Long userId, Long productId, ProductRateDTO productRateDTO) {
        log.info("Get product by id: {}", productId);
        Product product = productRepository.findById(productId)
                        .orElseThrow(() -> new EntityNotFoundException("Product doesn't exist!"));

        ProductReview productReview = ProductReview.builder()
                .product(product)
                .userId(userId)
                .rate(productRateDTO.getRate())
                .comment(productRateDTO.getComment())
                .pros(productRateDTO.getPros())
                .cons(productRateDTO.getCons())
                .build();

        log.info("Create product rate for product with id: {}", productId);
        productRateRepository.save(productReview);
    }

    @Transactional
    @Override
    public void changeReview(Long userId, Long productId, ProductRateDTO productRateDTO) {
        log.info("Get product by id: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product doesn't exist!"));

        log.info("Get product with user id: {} and product id: {}", userId, productId);
        productRateRepository.findProductRateByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new EntityNotFoundException("Product rate doesn't exist!"));

        ProductReview updatedProductReview = ProductReview.builder()
                .product(product)
                .userId(userId)
                .rate(productRateDTO.getRate())
                .comment(productRateDTO.getComment())
                .pros(productRateDTO.getPros())
                .cons(productRateDTO.getCons())
                .build();

        log.info("Save updated product rate");
        productRateRepository.save(updatedProductReview);
    }

    @Override
    public void deleteReview(Long userId, Long productId) {
        log.info("Get product by id: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product doesn't exist!"));

        log.info("Get product with user id: {} and product id: {}", userId, productId);
        ProductReview productReview = productRateRepository.findProductRateByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new EntityNotFoundException("Product rate doesn't exist!"));

        log.info("Remove product rate for product with id: {} from user: {}", productId, userId);
        productRateRepository.delete(productReview);
    }
}
