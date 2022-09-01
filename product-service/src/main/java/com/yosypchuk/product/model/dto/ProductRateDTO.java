package com.yosypchuk.product.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class ProductRateDTO {
    @NotBlank(message = "${product.review.user.not-blank}")
    private Long userId;

    @Min(value = 0)
    @Max(value = 5)
    @NotBlank(message = "${product.review.rate.not-blank}")
    private Integer rate;

    @Size(min = 10, max = 1024)
    @NotBlank(message = "${product.review.comment.not-blank}")
    private String comment;

    @Size(min = 10, max = 256)
    @NotBlank(message = "${product.review.pros.not-blank}")
    private String pros;

    @Size(min = 10, max = 256)
    @NotBlank(message = "${product.review.cons.not-blank}")
    private String cons;
}
