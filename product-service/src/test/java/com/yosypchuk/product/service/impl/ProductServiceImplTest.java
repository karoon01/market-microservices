package com.yosypchuk.product.service.impl;

import com.yosypchuk.product.exception.EntityNotFoundException;
import com.yosypchuk.product.model.Product;
import com.yosypchuk.product.model.dto.ProductDTO;
import com.yosypchuk.product.repository.ProductRepository;
import com.yosypchuk.product.test.util.TestProductDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.yosypchuk.product.test.util.TestProductDataUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;

    @Test
    void createProduct() {
        //given
        Product expectedProduct = TestProductDataUtil.createProduct();
        ProductDTO productBody = TestProductDataUtil.createProductDto();

        when(productRepository.findProductByName(MOCK_NAME)).thenReturn(Optional.empty());
        when(productRepository.save(any())).thenReturn(Optional.of(expectedProduct));

        //when
        ProductDTO actualProduct = productService.createProduct(productBody);

        //then
        assertThat(actualProduct, allOf(
                hasProperty("id", equalTo(expectedProduct.getId())),
                hasProperty("name", equalTo(expectedProduct.getName())),
                hasProperty("description", equalTo(expectedProduct.getDescription())),
                hasProperty("categories", equalTo(expectedProduct.getCategories())),
                hasProperty("price", equalTo(expectedProduct.getPrice())),
                hasProperty("amount", equalTo(expectedProduct.getAmount()))
        ));
    }

    @Test
    void testGetProductById() {
        //given
        Product expectedProduct = TestProductDataUtil.createProduct();

        when(productRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedProduct));

        //when
        ProductDTO actualProduct = productService.getProductById(MOCK_ID);

        //then
        assertThat(actualProduct, allOf(
                hasProperty("id", equalTo(expectedProduct.getId())),
                hasProperty("name", equalTo(expectedProduct.getName())),
                hasProperty("description", equalTo(expectedProduct.getDescription())),
                hasProperty("categories", equalTo(expectedProduct.getCategories())),
                hasProperty("price", equalTo(expectedProduct.getPrice())),
                hasProperty("amount", equalTo(expectedProduct.getAmount()))
        ));
    }

    @Test
    void testGetProductByIdThrowsExceptionIfProductDoesntExist() {
        //when
        when(productRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> productService.getProductById(MOCK_ID));
    }

    @Test
    void testGetAllProducts() {
        //given
        Product firstProduct = TestProductDataUtil.createProduct();
        Product secondProduct = TestProductDataUtil.createSecondProduct();
        List<Product> productList = List.of(firstProduct, secondProduct);

        when(productRepository.findAll()).thenReturn(productList);

        //when
        List<ProductDTO> products = productService.getAllProducts();

        //then
        assertThat(products, hasSize(2));
    }

    @Test
    void testUpdateProduct() {
        //given
        Product product = TestProductDataUtil.createProduct();
        Product expectedProduct = TestProductDataUtil.createUpdateProduct();
        ProductDTO updateBody = TestProductDataUtil.createUpdateProductDto();

        when(productRepository.findById(MOCK_ID)).thenReturn(Optional.of(product));
        when(productRepository.save(any())).thenReturn(expectedProduct);

        //when
        ProductDTO actualProduct = productService.updateProduct(MOCK_ID, updateBody);

        //then
        assertThat(actualProduct, allOf(
                hasProperty("id", equalTo(expectedProduct.getId())),
                hasProperty("name", equalTo(expectedProduct.getName())),
                hasProperty("description", equalTo(expectedProduct.getDescription())),
                hasProperty("categories", equalTo(expectedProduct.getCategories())),
                hasProperty("price", equalTo(expectedProduct.getPrice())),
                hasProperty("amount", equalTo(expectedProduct.getAmount()))
        ));
    }

    @Test
    void testUpdateProductThrowsExceptionIfProductDoesntExist() {
        //given
        ProductDTO updateBody = TestProductDataUtil.createUpdateProductDto();

        //when
        when(productRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> productService.updateProduct(MOCK_ID, updateBody));
    }

    @Test
    void testUpdateProductAmount() {
        //given
        Product product = TestProductDataUtil.createProduct();
        Product updatedProduct = TestProductDataUtil.createUpdateProduct();

        when(productRepository.findById(MOCK_ID)).thenReturn(Optional.of(product));
        when(productRepository.save(any())).thenReturn(updatedProduct);

        //when
        ProductDTO actualProduct = productService.updateProductAmount(MOCK_ID, MOCK_AMOUNT_UPDATED);

        //then
        assertEquals(updatedProduct.getAmount(), actualProduct.getAmount());
    }

    @Test
    void testUpdateProductAmountThrowsExceptionIfProductDoesntExist() {
        //when
        when(productRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> productService.updateProductAmount(MOCK_ID, MOCK_AMOUNT_UPDATED));
    }

    @Test
    void testRemoveProduct() {
        //given
        Product product = TestProductDataUtil.createProduct();

        when(productRepository.findById(MOCK_ID)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).delete(product);

        //when
        productService.removeProduct(MOCK_ID);

        //then
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void testRemoveProductThrowsExceptionIfProductDoesntExist() {
        //when
        when(productRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> productService.removeProduct(MOCK_ID));
    }
}
