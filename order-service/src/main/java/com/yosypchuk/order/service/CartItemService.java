package com.yosypchuk.order.service;

import com.yosypchuk.order.model.CartItem;
import com.yosypchuk.order.model.dto.CartItemRequest;

import java.util.List;

public interface CartItemService {

    void addCartItem(Long userId, Long productId);

    CartItem getCartItemById(Long cartItemId);

    void deleteCartItemFromCart(Long cartItemId);

    void deleteAllCartItems(Long cartId);

    List<CartItem> getAllCartItemsForCart(Long cartId);
}
