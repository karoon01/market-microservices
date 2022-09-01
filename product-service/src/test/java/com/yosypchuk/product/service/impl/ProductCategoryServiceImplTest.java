package com.yosypchuk.product.service.impl;

import com.yosypchuk.product.exception.EntityAlreadyExistException;
import com.yosypchuk.product.exception.EntityNotFoundException;
import com.yosypchuk.product.model.Product;
import com.yosypchuk.product.model.ProductCategory;
import com.yosypchuk.product.model.dto.ProductCategoryDTO;
import com.yosypchuk.product.repository.ProductCategoryRepository;
import com.yosypchuk.product.repository.ProductRepository;
import com.yosypchuk.product.test.util.TestCategoryDataUtil;
import com.yosypchuk.product.test.util.TestProductDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.yosypchuk.product.test.util.TestCategoryDataUtil.MOCK_ID;
import static com.yosypchuk.product.test.util.TestCategoryDataUtil.MOCK_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductCategoryServiceImplTest {
    @InjectMocks
    private ProductCategoryServiceImpl productCategoryService;
    @Mock
    private ProductCategoryRepository productCategoryRepository;
    @Mock
    private ProductRepository productRepository;

    @Test
    void testCreateProductCategory() {
        //given
        ProductCategory expectedCategory = TestCategoryDataUtil.createCategory();
        ProductCategoryDTO categoryBody = TestCategoryDataUtil.createCategoryDto();

        when(productCategoryRepository.findProductCategoryByName(MOCK_NAME)).thenReturn(Optional.empty());
        when(productCategoryRepository.save(any())).thenReturn(expectedCategory);

        //when
        ProductCategoryDTO actualCategory = productCategoryService.createProductCategory(categoryBody);

        //then
        assertThat(actualCategory, allOf(
               hasProperty("id", equalTo(expectedCategory.getId())),
               hasProperty("name", equalTo(expectedCategory.getName()))
        ));
    }

    @Test
    void testCreateProductCategoryThrowsExceptionIfCategoryAlreadyExist() {
        //given
        ProductCategory expectedCategory = TestCategoryDataUtil.createCategory();
        ProductCategoryDTO categoryBody = TestCategoryDataUtil.createCategoryDto();

        //when
        when(productCategoryRepository.findProductCategoryByName(MOCK_NAME)).thenReturn(Optional.of(expectedCategory));

        //then
        assertThrows(EntityAlreadyExistException.class, () -> productCategoryService.createProductCategory(categoryBody));
    }

    @Test
    void testGetAllProductCategories() {
        //given
        ProductCategory expectedCategory = TestCategoryDataUtil.createCategory();
        List<ProductCategory> categoryList = List.of(expectedCategory);

        when(productCategoryRepository.findAll()).thenReturn(categoryList);

        //when
        List<ProductCategoryDTO> categories = productCategoryService.getAllProductCategories();

        //then
        assertThat(categories, hasSize(1));
        verify(productCategoryRepository, times(1)).findAll();
    }

    @Test
    void testRemoveProductCategory() {
        //given
        ProductCategory expectedCategory = TestCategoryDataUtil.createCategory();

        when(productCategoryRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedCategory));
        doNothing().when(productCategoryRepository).delete(any());

        //when
        productCategoryService.removeProductCategory(MOCK_ID);

        //then
        verify(productCategoryRepository, times(1)).delete(expectedCategory);
    }

    @Test
    void testRemoveProductCategoryThrowsExceptionIfCategoryDoesntExist() {
        //when
        when(productCategoryRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> productCategoryService.removeProductCategory(MOCK_ID));
    }

    @Test
    void testAddCategoryToProduct() {
        //given
        Product product = TestProductDataUtil.createProduct();
        ProductCategory productCategory = TestCategoryDataUtil.createCategory();

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productCategoryRepository.findById(MOCK_ID)).thenReturn(Optional.of(productCategory));
        doNothing().when(productCategoryRepository).addProductCategory(product.getId(), productCategory.getId());

        //when
        productCategoryService.addCategoryToProduct(product.getId(), productCategory.getId());

        //then
        verify(productCategoryRepository, times(1)).addProductCategory(product.getId(), productCategory.getId());
    }

    @Test
    void testAddCategoryToProductThrowsExceptionIfProductDoesntExist() {
        //given
        Product product = TestProductDataUtil.createProduct();
        ProductCategory productCategory = TestCategoryDataUtil.createCategory();

        //when
        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> productCategoryService.addCategoryToProduct(product.getId(), productCategory.getId()));
    }

    @Test
    void testAddCategoryToProductThrowsExceptionIfCategoryDoesntExist() {
        //given
        Product product = TestProductDataUtil.createProduct();
        ProductCategory productCategory = TestCategoryDataUtil.createCategory();

        //when
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productCategoryRepository.findById(productCategory.getId())).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> productCategoryService.addCategoryToProduct(product.getId(), productCategory.getId()));
    }

    @Test
    void testRemoveCategoryFromProduct() {
        //given
        Product product = TestProductDataUtil.createProduct();
        ProductCategory productCategory = TestCategoryDataUtil.createCategory();

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productCategoryRepository.findById(MOCK_ID)).thenReturn(Optional.of(productCategory));
        doNothing().when(productCategoryRepository).removeProductCategory(product.getId(), productCategory.getId());

        //when
        productCategoryService.removeCategoryFromProduct(product.getId(), productCategory.getId());

        //then
        verify(productCategoryRepository, times(1)).removeProductCategory(product.getId(), productCategory.getId());
    }

    @Test
    void testRemoveCategoryFromProductThrowsExceptionIfProductDoesntExist() {
        //given
        Product product = TestProductDataUtil.createProduct();
        ProductCategory productCategory = TestCategoryDataUtil.createCategory();

        //when
        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> productCategoryService.removeCategoryFromProduct(product.getId(), productCategory.getId()));
    }

    @Test
    void testRemoveCategoryFromProductThrowsExceptionIfCategoryDoesntExist() {
        //given
        Product product = TestProductDataUtil.createProduct();
        ProductCategory productCategory = TestCategoryDataUtil.createCategory();

        //when
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productCategoryRepository.findById(productCategory.getId())).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> productCategoryService.removeCategoryFromProduct(product.getId(), productCategory.getId()));
    }
}
