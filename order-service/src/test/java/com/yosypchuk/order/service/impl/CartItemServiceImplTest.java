package com.yosypchuk.order.service.impl;

import com.yosypchuk.order.exception.EntityNotFoundException;
import com.yosypchuk.order.model.Cart;
import com.yosypchuk.order.model.CartItem;
import com.yosypchuk.order.repository.CartItemRepository;
import com.yosypchuk.order.repository.CartRepository;
import com.yosypchuk.order.util.TestCartDataUtil;
import com.yosypchuk.order.util.TestCartItemDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.yosypchuk.order.util.TestCartDataUtil.MOCK_ID;
import static com.yosypchuk.order.util.TestCartDataUtil.MOCK_USER_ID;
import static com.yosypchuk.order.util.TestCartItemDataUtil.MOCK_CART;
import static com.yosypchuk.order.util.TestCartItemDataUtil.MOCK_PRODUCT_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartItemServiceImplTest {
    @InjectMocks
    private CartItemServiceImpl cartItemService;
    @InjectMocks
    private CartServiceImpl cartService;
    @Mock
    private CartItemRepository cartItemRepository;
    @Mock
    private CartRepository cartRepository;


    @Test
    void testAddCartItem() {
        //given
        CartItem cartItem = TestCartItemDataUtil.createCartItem();
        Cart cart = TestCartDataUtil.createCart();

        when(cartRepository.findCartByUserId(MOCK_USER_ID)).thenReturn(Optional.of(cart));
        when(cartItemRepository.save(any())).thenReturn(cartItem);

        //when
        cartItemService.addCartItem(MOCK_USER_ID, MOCK_PRODUCT_ID);

        //then
        verify(cartItemRepository, times(1)).save(cartItem);
    }

    @Test
    void testGetCartItemById() {
        //given
        CartItem expectedCartItem = TestCartItemDataUtil.createCartItem();

        when(cartItemRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedCartItem));

        //when
        CartItem actualCartItem = cartItemService.getCartItemById(MOCK_ID);

        //then
        assertThat(actualCartItem, allOf(
                hasProperty("id", equalTo(expectedCartItem.getId())),
                hasProperty("cart", equalTo(expectedCartItem.getCart())),
                hasProperty("productId", equalTo(expectedCartItem.getProductId())),
                hasProperty("price", equalTo(expectedCartItem.getPrice()))
        ));
    }

    @Test
    void testDeleteCartItemFromCart() {
        //given
        CartItem cartItem = TestCartItemDataUtil.createCartItem();

        when(cartItemRepository.findById(MOCK_ID)).thenReturn(Optional.of(cartItem));
        doNothing().when(cartItemRepository).delete(any());

        //when
        cartItemService.deleteCartItemFromCart(MOCK_ID);

        //then
        verify(cartItemRepository, times(1)).delete(cartItem);
    }

    @Test
    void testDeleteCartItemFromCartThrowsExceptionIfCartItemDoesntExist() {
        //when
        when(cartItemRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> cartItemService.deleteCartItemFromCart(MOCK_ID));
    }

    @Test
    void testDeleteAllCartItems() {
        //given
        Cart cart = TestCartDataUtil.createCart();

        when(cartRepository.findById(MOCK_CART.getId())).thenReturn(Optional.of(cart));
        doNothing().when(cartItemRepository).deleteAllCartItemsByCartId(any());

        //when
        cartItemService.deleteAllCartItems(cart.getId());

        //then
        verify(cartItemRepository, times(1)).deleteAllCartItemsByCartId(cart.getId());
    }

    @Test
    void testDeleteAllCartItemsThrowsExceptionIfCartDoesntExist() {
        //when
        when(cartRepository.findById(MOCK_CART.getId())).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> cartItemService.deleteAllCartItems(MOCK_CART.getId()));
    }
}
