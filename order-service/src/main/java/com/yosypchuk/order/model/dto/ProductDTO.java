package com.yosypchuk.order.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;

    @NotBlank(message = "${product.name.not-blank}")
    private String name;

    @NotNull(message = "${product.category.not-blank}")
    private ProductCategoryDTO category;

    @NotBlank(message = "${product.description.not-blank}")
    private String description;

    private Double averageRate;

    @Min(value = 0, message = "${product.price.positive}")
    private Double price;

    @Min(value = 0L, message = "${product.amount.positive}")
    private Integer amount;
}
