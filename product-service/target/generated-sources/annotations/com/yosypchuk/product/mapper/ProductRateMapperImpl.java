package com.yosypchuk.product.mapper;

import com.yosypchuk.product.model.ProductReview;
import com.yosypchuk.product.model.dto.ProductRateDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-28T19:39:04+0300",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.16.1 (Amazon.com Inc.)"
)
@Component
public class ProductRateMapperImpl implements ProductRateMapper {

    @Override
    public ProductRateDTO mapProductRateDto(ProductReview productReview) {
        if ( productReview == null ) {
            return null;
        }

        ProductRateDTO.ProductRateDTOBuilder productRateDTO = ProductRateDTO.builder();

        productRateDTO.userId( productReview.getUserId() );
        productRateDTO.rate( productReview.getRate() );
        productRateDTO.comment( productReview.getComment() );
        productRateDTO.pros( productReview.getPros() );
        productRateDTO.cons( productReview.getCons() );

        return productRateDTO.build();
    }

    @Override
    public ProductReview mapProductRate(ProductRateDTO productRateDTO) {
        if ( productRateDTO == null ) {
            return null;
        }

        ProductReview.ProductReviewBuilder productReview = ProductReview.builder();

        productReview.userId( productRateDTO.getUserId() );
        productReview.rate( productRateDTO.getRate() );
        productReview.comment( productRateDTO.getComment() );
        productReview.pros( productRateDTO.getPros() );
        productReview.cons( productRateDTO.getCons() );

        return productReview.build();
    }
}
