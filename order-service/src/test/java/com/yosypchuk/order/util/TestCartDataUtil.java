package com.yosypchuk.order.util;

import com.yosypchuk.order.model.Cart;
import com.yosypchuk.order.model.CartItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestCartDataUtil {
    public static Long MOCK_ID = 1L;
    public static List<CartItem> MOCK_CART_ITEMS = List.of(TestCartItemDataUtil.createCartItem());
    public static Long MOCK_USER_ID = 1L;
    public static Double MOCK_TOTAL_PRICE = 350.0;

    public static Cart createCart() {
        return Cart.builder()
                .id(MOCK_ID)
                .cartItems(MOCK_CART_ITEMS)
                .userId(MOCK_USER_ID)
                .totalPrice(MOCK_TOTAL_PRICE)
                .build();
    }
}
