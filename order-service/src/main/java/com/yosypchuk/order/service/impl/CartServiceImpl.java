package com.yosypchuk.order.service.impl;

import com.yosypchuk.order.exception.EntityAlreadyExistException;
import com.yosypchuk.order.exception.EntityNotFoundException;
import com.yosypchuk.order.model.Cart;
import com.yosypchuk.order.model.CartItem;
import com.yosypchuk.order.repository.CartItemRepository;
import com.yosypchuk.order.repository.CartRepository;
import com.yosypchuk.order.service.CartItemService;
import com.yosypchuk.order.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    @Override
    public Cart createCart(Long userId) {
        log.info("Get cart by user id: {]", userId);
        Optional<Cart> possibleCart = cartRepository.findCartByUserId(userId);

        if(possibleCart.isPresent()) {
            log.warn("Cart for user with id: {} is already exist", userId);
            throw new EntityAlreadyExistException("Cart is already exist!");
        }

        log.info("Create a new cart for user with id: {}", userId);
        Cart cart = Cart.builder()
                .cartItems(Collections.emptyList())
                .userId(userId)
                .totalPrice(0.0)
                .build();

        log.info("Create cart for user with id: {}", userId);
        cartRepository.save(cart);

        return cart;
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        log.info("Get cart by user id: {}", userId);
        return cartRepository.findCartByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cart doesn't exist!"));
    }

    @Override
    public Cart getCartById(Long id) {
        log.info("Get cart by id: {}", id);
        return cartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cart doesn't exist!"));
    }
}
