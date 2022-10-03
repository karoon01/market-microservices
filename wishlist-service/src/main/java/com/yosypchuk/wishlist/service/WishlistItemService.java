package com.yosypchuk.wishlist.service;

import com.yosypchuk.wishlist.model.WishlistItem;

public interface WishlistItemService {

    void addWishlistItem(Long userId, Long productId);

    WishlistItem getWishlistItemById(Long id);

    void removeWishlistItem(Long wishlistItemId);

    void removeAllWishlistItems(Long wishlistId);
}
