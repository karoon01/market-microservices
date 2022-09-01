package com.yosypchuk.order.controller;

import com.yosypchuk.order.api.CartApi;
import com.yosypchuk.order.model.Cart;
import com.yosypchuk.order.model.dto.CartItemRequest;
import com.yosypchuk.order.service.CartItemService;
import com.yosypchuk.order.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CartController implements CartApi {

    private final CartService cartService;
    private final CartItemService cartItemService;

    @Override
    public Cart createCart(Long userId) {
        return cartService.createCart(userId);
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @Override
    public Cart getCartById(Long id) {
        return cartService.getCartById(id);
    }

    @Override
    public ResponseEntity<Void> addItemToCart(Long userId, CartItemRequest cartItemRequest) {
        cartItemService.addCartItem(userId, cartItemRequest);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> removeItemFromCart(Long cartItemId) {
        cartItemService.deleteCartItemFromCart(cartItemId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> removeAllItemsFromCart(Long cartId) {
        cartItemService.deleteAllCartItems(cartId);
        return ResponseEntity.noContent().build();
    }


}
