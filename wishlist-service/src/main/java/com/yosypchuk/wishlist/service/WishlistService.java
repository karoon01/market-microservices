package com.yosypchuk.wishlist.service;

import com.yosypchuk.wishlist.model.Wishlist;

public interface WishlistService {

    Wishlist createWishlist(Long userId);

    Wishlist getWishlistByUserId(Long userId);

    Wishlist getWishlistById(Long wishlistId);
}
