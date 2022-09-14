package com.yosypchuk.order.util;

import com.yosypchuk.order.model.Cart;
import com.yosypchuk.order.model.CartItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestCartItemDataUtil {
    public static Long MOCK_ID = 1L;
    public static Cart MOCK_CART = TestCartDataUtil.createCart();
    public static Long MOCK_PRODUCT_ID = 1L;
    public static Integer MOCK_AMOUNT = 2;
    public static Double MOCK_PRICE = 346.0;

    public static CartItem createCartItem() {
        return CartItem.builder()
                .id(MOCK_ID)
                .cart(MOCK_CART)
                .productId(MOCK_PRODUCT_ID)
                .amount(MOCK_AMOUNT)
                .price(MOCK_PRICE)
                .build();
    }
}
