package com.yosypchuk.order.service.impl;

import com.yosypchuk.order.client.ProductFeignClient;
import com.yosypchuk.order.exception.EntityNotFoundException;
import com.yosypchuk.order.model.Cart;
import com.yosypchuk.order.model.CartItem;
import com.yosypchuk.order.model.dto.CartItemRequest;
import com.yosypchuk.order.model.dto.ProductDTO;
import com.yosypchuk.order.repository.CartItemRepository;
import com.yosypchuk.order.repository.CartRepository;
import com.yosypchuk.order.service.CartItemService;
import com.yosypchuk.order.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartService cartService;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductFeignClient productClient;

    @Transactional
    @Override
    public void addCartItem(Long userId, Long productId) {
        log.info("Get cart by user id: {}", userId);
        Cart cart = cartRepository.findCartByUserId(userId)
                .orElse(null);

        if(cart == null) {
            log.info("Create cart for user with id: {}", userId);
            cart = cartService.createCart(userId);
        }

        ProductDTO product = productClient.getProduct(productId);

        //if product is already exist in cart

        if(cart.getCartItems() != null) {
            for(CartItem cartItem : cart.getCartItems()) {
                if(product.getId().equals(cartItem.getProductId())) {
                    log.info("Update amount and total price in cart for product: {}", productId);
                    cartItem.setAmount(product.getAmount() + 1);
                    cartItem.setPrice(product.getPrice() * cartItem.getAmount());

                    cartItemRepository.save(cartItem);
                    return;
                }
            }
        }

        //if cart doesn't have any cartItem

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .productId(productId)
                .price(product.getPrice())
                .amount(1)
                .build();

        log.info("Save cartItem to database");
        cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem getCartItemById(Long cartItemId) {
        log.info("Get cartItem by id: {}", cartItemId);

        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("CartItem doesn't exist!"));
    }

    @Transactional
    @Override
    public void deleteCartItemFromCart(Long cartItemId) {
        log.info("Get CartItem by id: {}", cartItemId);
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("CartItem doesn't exist!"));

        log.info("Delete cartItem from cart");
        cartItemRepository.delete(cartItem);
    }

    @Override
    public void deleteAllCartItems(Long cartId) {
        log.info("Get Cart by id: {}", cartId);
        cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart doesn't exist!"));

        log.info("Delete all CartItem's for cart with id: {}", cartId);
        cartItemRepository.deleteAllCartItemsByCartId(cartId);
    }
}
