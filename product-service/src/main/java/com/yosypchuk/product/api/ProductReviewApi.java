package com.yosypchuk.product.api;

import com.yosypchuk.product.model.dto.ProductRateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api("Product rate management API")
@RequestMapping("/product/rate")
public interface ProductReviewApi {

    @ApiOperation("Create product rate")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userId}/{productId}")
    @ApiResponse(code = 201, message = "Created")
    ResponseEntity<Void> createProductReview(@PathVariable Long userId, @PathVariable Long productId, @RequestBody @Valid ProductRateDTO productRateDTO);

    @ApiOperation("Update product rate")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{userId}/{productId}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    ResponseEntity<Void> updateProductReview(@PathVariable Long userId, @PathVariable Long productId, @RequestBody @Valid ProductRateDTO productRateDTO);

    @ApiOperation("Delete product rate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{userId}/{productId}")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    ResponseEntity<Void> deleteProductReview(@PathVariable Long userId, @PathVariable Long productId);
}
