package com.yosypchuk.product.mapper;

import com.yosypchuk.product.model.ProductReview;
import com.yosypchuk.product.model.dto.ProductRateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductRateMapper {
    ProductRateMapper INSTANCE = Mappers.getMapper(ProductRateMapper.class);

    ProductRateDTO mapProductRateDto(ProductReview productReview);
    ProductReview mapProductRate(ProductRateDTO productRateDTO);
}
