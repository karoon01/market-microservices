package com.yosypchuk.order.service.impl;

import com.yosypchuk.order.exception.EntityAlreadyExistException;
import com.yosypchuk.order.exception.EntityNotFoundException;
import com.yosypchuk.order.model.Cart;
import com.yosypchuk.order.repository.CartRepository;
import com.yosypchuk.order.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

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
                .build();

        log.info("Create cart for user with id: {}", userId);
        cartRepository.save(cart);

        return cart;
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        log.info("Get cart by user id: {}", userId);
        Cart cart = cartRepository.findCartByUserId(userId)
                .orElse(null);

        return cart;
    }

    @Override
    public Cart getCartById(Long id) {
        log.info("Get cart by id: {}", id);
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cart doesn't exist!"));

        return cart;
    }
}
