package com.yosypchuk.product.mapper;

import com.yosypchuk.product.model.ProductCategory;
import com.yosypchuk.product.model.dto.ProductCategoryDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-23T12:28:12+0300",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.13 (Amazon.com Inc.)"
)
@Component
public class ProductCategoryMapperImpl implements ProductCategoryMapper {

    @Override
    public ProductCategory mapProductCategory(ProductCategoryDTO productCategoryDTO) {
        if ( productCategoryDTO == null ) {
            return null;
        }

        ProductCategory.ProductCategoryBuilder productCategory = ProductCategory.builder();

        productCategory.id( productCategoryDTO.getId() );
        productCategory.name( productCategoryDTO.getName() );

        return productCategory.build();
    }

    @Override
    public ProductCategoryDTO mapProductCategoryDto(ProductCategory productCategory) {
        if ( productCategory == null ) {
            return null;
        }

        ProductCategoryDTO.ProductCategoryDTOBuilder productCategoryDTO = ProductCategoryDTO.builder();

        productCategoryDTO.id( productCategory.getId() );
        productCategoryDTO.name( productCategory.getName() );

        return productCategoryDTO.build();
    }
}
