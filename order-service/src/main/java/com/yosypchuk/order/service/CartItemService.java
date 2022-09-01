package com.yosypchuk.order.service;

import com.yosypchuk.order.model.CartItem;
import com.yosypchuk.order.model.dto.CartItemRequest;

public interface CartItemService {

    void addCartItem(Long userId, CartItemRequest cartItemRequest);

    CartItem getCartItemById(Long cartItemId);

    void deleteCartItemFromCart(Long cartItemId);

    void deleteAllCartItems(Long cartId);
}
