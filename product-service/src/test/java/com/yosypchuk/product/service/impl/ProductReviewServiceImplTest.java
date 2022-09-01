package com.yosypchuk.product.service.impl;

import com.yosypchuk.product.exception.EntityNotFoundException;
import com.yosypchuk.product.model.Product;
import com.yosypchuk.product.model.ProductReview;
import com.yosypchuk.product.model.dto.ProductRateDTO;
import com.yosypchuk.product.repository.ProductRateRepository;
import com.yosypchuk.product.repository.ProductRepository;
import com.yosypchuk.product.test.util.TestProductDataUtil;
import com.yosypchuk.product.test.util.TestReviewDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.yosypchuk.product.test.util.TestReviewDataUtil.MOCK_USER_ID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductReviewServiceImplTest {
    @InjectMocks
    private ProductReviewServiceImpl productRateService;
    @Mock
    private ProductRateRepository productRateRepository;
    @Mock
    private ProductRepository productRepository;

    @Test
    void testCreateRate() {
        //given
        Product product = TestProductDataUtil.createProduct();
        ProductReview expectedProductReview = TestReviewDataUtil.createRate();
        ProductRateDTO productRateBody = TestReviewDataUtil.createRateDTO();

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productRateRepository.save(any())).thenReturn(expectedProductReview);

        //when
        productRateService.createReview(MOCK_USER_ID, product.getId(), productRateBody);

        //then
        verify(productRateRepository, times(1)).save(any());
    }

    @Test
    void testCreateRateThrowsExceptionIfProductDoesntExist() {
        //given
        Product product = TestProductDataUtil.createProduct();
        ProductRateDTO productRateBody = TestReviewDataUtil.createRateDTO();

        //when
        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> productRateService.createReview(MOCK_USER_ID, product.getId(), productRateBody));
    }

    @Test
    void testChangeRate() {
        //given
        Product product = TestProductDataUtil.createProduct();
        ProductReview productReview = TestReviewDataUtil.createRate();

        ProductReview updatedRate = TestReviewDataUtil.createUpdatedRate();
        ProductRateDTO updateBody = TestReviewDataUtil.createUpdatedRateDTO();

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productRateRepository.findProductRateByUserIdAndProductId(MOCK_USER_ID, product.getId())).thenReturn(Optional.of(productReview));
        when(productRateRepository.save(any())).thenReturn(updatedRate);

        //when
        productRateService.changeReview(MOCK_USER_ID, product.getId(), updateBody);

        //then
        verify(productRateRepository, times(1)).save(any());
    }

    @Test
    void testChangeRateThrowsExceptionIfProductDoesntExist() {
        //given
        Product product = TestProductDataUtil.createProduct();
        ProductRateDTO updateBody = TestReviewDataUtil.createUpdatedRateDTO();

        //when
        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> productRateService.changeReview(MOCK_USER_ID, product.getId(), updateBody));
    }

    @Test
    void testChangeRateThrowsExceptionIfRateDoesntExist() {
        //given
        Product product = TestProductDataUtil.createProduct();
        ProductRateDTO updateBody = TestReviewDataUtil.createUpdatedRateDTO();

        //when
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productRateRepository.findProductRateByUserIdAndProductId(MOCK_USER_ID, product.getId())).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> productRateService.changeReview(MOCK_USER_ID, product.getId(), updateBody));
    }
}
