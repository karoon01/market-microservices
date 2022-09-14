package com.yosypchuk.wishlist.util;

import com.yosypchuk.wishlist.model.Wishlist;
import com.yosypchuk.wishlist.model.WishlistItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestWishlistDataUtil {
    public static Long MOCK_ID = 1L;
    public static List<WishlistItem> MOCK_WISHLIST_ITEMS = List.of();
    public static Long MOCK_USER_ID = 1L;

    public static Wishlist createWishlist() {
        return Wishlist.builder()
                .id(MOCK_ID)
                .wishlistItems(MOCK_WISHLIST_ITEMS)
                .userId(MOCK_USER_ID)
                .build();
    }
}
