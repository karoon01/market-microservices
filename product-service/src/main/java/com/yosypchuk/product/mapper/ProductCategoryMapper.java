package com.yosypchuk.product.mapper;

import com.yosypchuk.product.model.ProductCategory;
import com.yosypchuk.product.model.dto.ProductCategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductCategoryMapper {
    ProductCategoryMapper INSTANCE = Mappers.getMapper(ProductCategoryMapper.class);

    ProductCategory mapProductCategory(ProductCategoryDTO productCategoryDTO);
    ProductCategoryDTO mapProductCategoryDto(ProductCategory productCategory);
}
