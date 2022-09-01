package com.yosypchuk.product.controller;

import com.yosypchuk.product.api.ProductApi;
import com.yosypchuk.product.model.dto.ProductDTO;
import com.yosypchuk.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController implements ProductApi {

    private final ProductService productService;

    @Override
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @Override
    public ProductDTO getProduct(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @Override
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @Override
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.removeProduct(id);
        return ResponseEntity.noContent().build();
    }
}