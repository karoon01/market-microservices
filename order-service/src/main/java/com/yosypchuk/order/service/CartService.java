package com.yosypchuk.order.service;

import com.yosypchuk.order.model.Cart;

public interface CartService {
    Cart createCart(Long userId);

    Cart getCartByUserId(Long userId);

    Cart getCartById(Long id);
}
