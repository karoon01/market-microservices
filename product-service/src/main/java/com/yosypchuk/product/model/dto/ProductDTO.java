package com.yosypchuk.product.model.dto;

import com.yosypchuk.product.model.ProductCategory;
import com.yosypchuk.product.model.ProductReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;

    @NotBlank(message = "${product.name.not-blank}")
    private String name;

    @NotNull(message = "${product.category.not-blank}")
    private List<ProductCategory> categories;

    @NotBlank(message = "${product.description.not-blank}")
    private String description;

    private List<ProductReview> reviews;

    @Min(value = 0, message = "${product.price.positive}")
    private Double price;

    @Min(value = 0L, message = "${product.amount.positive}")
    private Integer amount;
}
