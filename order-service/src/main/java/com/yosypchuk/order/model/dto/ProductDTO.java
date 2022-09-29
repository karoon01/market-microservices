package com.yosypchuk.order.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;

    private String name;
    private ProductCategoryDTO category;
    private String description;
    private Double averageRate;
    private Double price;
    private Integer amount;
}
