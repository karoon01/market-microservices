package com.yosypchuk.wishlist.service;

import com.yosypchuk.wishlist.model.WishlistItem;
import com.yosypchuk.wishlist.model.dto.WishlistItemRequest;

public interface WishlistItemService {

    void addWishlistItem(Long userId, WishlistItemRequest wishlistItemRequest);

    WishlistItem getWishlistItemById(Long id);

    void removeWishlistItem(Long wishlistItemId);

    void removeAllWishlistItems(Long wishlistId);
}
