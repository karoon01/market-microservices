package com.yosypchuk.product.service;


import com.yosypchuk.product.model.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO getProductById(Long id);

    ProductDTO getProductByName(String name);

    ProductDTO updateProduct(Long id, ProductDTO productDTO);

    ProductDTO updateProductAmount(Long id, Integer amount);

    void removeProduct(Long id);

    List<ProductDTO> getAllProducts();
}
