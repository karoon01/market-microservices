package com.yosypchuk.wishlist.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishlistItemRequest {
    @NotBlank(message = "${wishlist.product-id.not-blank}")
    private Long productId;
}
