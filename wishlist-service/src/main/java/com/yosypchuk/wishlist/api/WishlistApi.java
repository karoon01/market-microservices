package com.yosypchuk.wishlist.api;

import com.yosypchuk.wishlist.model.Wishlist;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("Wishlist management API")
@RequestMapping("/wishlist")
public interface WishlistApi {

    @ApiOperation("Create wishlist")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userId}")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 409, message = "Conflict")
    })
    Wishlist createWishlist(@PathVariable Long userId);

    @ApiOperation("Get wishlist by userId")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    @ApiResponse(code = 200, message = "OK")
    Wishlist getWishlistByUserId(@PathVariable Long userId);

    @ApiOperation("Add product to wishlist")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/{userId}/add/{productId}")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Accepted"),
            @ApiResponse(code = 409, message = "Conflict")
    })
    ResponseEntity<Void> addProductToWishlist(@PathVariable Long userId, @PathVariable Long productId);

    @ApiOperation("Remove product from wishlist")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{wishlistItemId}/remove")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Accepted"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    ResponseEntity<Void> removeProductFromWishlist(@PathVariable Long wishlistItemId);

    @ApiOperation("Remove all products from wishlist")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{wishlistId}/remove/all")
    @ApiResponse(code = 202, message = "Accepted")
    ResponseEntity<Void> removeAllProductsFromWishList(@PathVariable Long wishlistId);
}
