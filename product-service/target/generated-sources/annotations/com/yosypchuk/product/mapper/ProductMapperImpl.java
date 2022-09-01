package com.yosypchuk.product.mapper;

import com.yosypchuk.product.model.Product;
import com.yosypchuk.product.model.ProductCategory;
import com.yosypchuk.product.model.ProductReview;
import com.yosypchuk.product.model.dto.ProductDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-23T12:28:13+0300",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.13 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO mapProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO.ProductDTOBuilder productDTO = ProductDTO.builder();

        productDTO.id( product.getId() );
        productDTO.name( product.getName() );
        List<ProductCategory> list = product.getCategories();
        if ( list != null ) {
            productDTO.categories( new ArrayList<ProductCategory>( list ) );
        }
        productDTO.description( product.getDescription() );
        List<ProductReview> list1 = product.getRates();
        if ( list1 != null ) {
            productDTO.rates( new ArrayList<ProductReview>( list1 ) );
        }
        productDTO.price( product.getPrice() );
        productDTO.amount( product.getAmount() );

        return productDTO.build();
    }

    @Override
    public Product mapProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( productDTO.getId() );
        product.name( productDTO.getName() );
        product.description( productDTO.getDescription() );
        List<ProductCategory> list = productDTO.getCategories();
        if ( list != null ) {
            product.categories( new ArrayList<ProductCategory>( list ) );
        }
        List<ProductReview> list1 = productDTO.getRates();
        if ( list1 != null ) {
            product.rates( new ArrayList<ProductReview>( list1 ) );
        }
        product.price( productDTO.getPrice() );
        product.amount( productDTO.getAmount() );

        return product.build();
    }
}
