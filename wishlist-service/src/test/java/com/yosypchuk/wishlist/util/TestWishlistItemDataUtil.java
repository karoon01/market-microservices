package com.yosypchuk.wishlist.util;

import com.yosypchuk.wishlist.model.Wishlist;
import com.yosypchuk.wishlist.model.WishlistItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestWishlistItemDataUtil {
    public static Long MOCK_ID = 1L;
    public static Wishlist MOCK_WISHLIST = TestWishlistDataUtil.createWishlist();
    public static Long MOCK_PRODUCT_ID = 1L;

    public static WishlistItem createWishlistItem() {
        return WishlistItem.builder()
                .id(MOCK_ID)
                .wishlist(MOCK_WISHLIST)
                .productId(MOCK_PRODUCT_ID)
                .build();
    }
}
