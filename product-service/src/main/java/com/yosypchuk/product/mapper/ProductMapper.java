package com.yosypchuk.product.mapper;

import com.yosypchuk.product.model.Product;
import com.yosypchuk.product.model.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO mapProductDto(Product product);
    Product mapProduct(ProductDTO productDTO);
}
