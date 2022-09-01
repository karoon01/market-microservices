package com.yosypchuk.product.service.impl;

import com.yosypchuk.product.exception.EntityAlreadyExistException;
import com.yosypchuk.product.exception.EntityNotFoundException;
import com.yosypchuk.product.mapper.ProductMapper;
import com.yosypchuk.product.model.Product;
import com.yosypchuk.product.model.dto.ProductDTO;
import com.yosypchuk.product.repository.ProductRepository;
import com.yosypchuk.product.service.ProductService;
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
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDTO getProductById(Long id) {
        log.info("Get product by id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product is not found!"));

        return ProductMapper.INSTANCE.mapProductDto(product);
    }

    @Override
    public ProductDTO getProductByName(String name) {
        log.info("Get product by name: {}", name);
        Product product = productRepository.findProductByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Product is not found!"));

        return ProductMapper.INSTANCE.mapProductDto(product);
    }

    @Transactional
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        String name = productDTO.getName();
        Optional<Product> possibleProduct = productRepository.findProductByName(name);

        if(possibleProduct.isPresent()) {
            log.warn("Product with name: {} is already exist!", name);
            throw new EntityAlreadyExistException("Product with this name is already exist");
        }

        Product product = ProductMapper.INSTANCE.mapProduct(productDTO);

        log.info("Save product");
        productRepository.save(product);

        return ProductMapper.INSTANCE.mapProductDto(product);
    }

    @Transactional
    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        log.info("Trying to get product with id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product is not found!"));

        log.info("Update product with id: {}", id);
        Product updatedProduct = Product.builder()
                .id(product.getId())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .categories(productDTO.getCategories())
                .rates(productDTO.getRates())
                .price(productDTO.getPrice())
                .amount(productDTO.getAmount())
                .build();

        productRepository.save(updatedProduct);

        return ProductMapper.INSTANCE.mapProductDto(updatedProduct);
    }

    @Transactional
    @Override
    public ProductDTO updateProductAmount(Long id, Integer amount) {
        log.info("Trying to get product with id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product is not found!"));
        product.setAmount(amount);

        log.info("Update amount for product with id: {}", id);
        productRepository.save(product);

        return ProductMapper.INSTANCE.mapProductDto(product);
    }

    @Transactional
    @Override
    public void removeProduct(Long id) {
        log.info("Get product by id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product is not found"));

        log.info("Delete product with id: {}", id);
        productRepository.delete(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        log.info("Get all products");
        return productRepository.findAll()
                .stream()
                .map(ProductMapper.INSTANCE::mapProductDto)
                .collect(Collectors.toList());
    }
}
