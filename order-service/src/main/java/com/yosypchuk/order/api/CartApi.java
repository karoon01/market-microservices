package com.yosypchuk.order.api;

import com.yosypchuk.order.model.Cart;
import com.yosypchuk.order.model.dto.CartItemRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("Cart management API")
@RequestMapping("/cart")
public interface CartApi {

    @ApiOperation("Create cart for user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userId}")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 409, message = "Conflict")
    })
    Cart createCart(@PathVariable Long userId);

    @ApiOperation("Get user cart")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{userId}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    Cart getCartByUserId(@PathVariable Long userId);

    @ApiOperation("Get cart by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    Cart getCartById(@PathVariable Long id);

    @ApiOperation("Add product to cart")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userId}/add/{productId}")
    @ApiResponse(code = 201, message = "Created")
    ResponseEntity<Void> addItemToCart(@PathVariable Long userId, @PathVariable Long productId);

    @ApiOperation("Remove product from cart")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{cartItemId}/remove")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartItemId);

    @ApiOperation("Remove all products from cart")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{cartId}/remove/all")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 404, message = "Not Found")
    })

    ResponseEntity<Void> removeAllItemsFromCart(@PathVariable Long cartId);
}
