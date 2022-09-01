package com.yosypchuk.product.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class ProductCategoryDTO {
    private Long id;

    @NotBlank(message = "${product.category.name.not-blank}")
    private String name;
}
