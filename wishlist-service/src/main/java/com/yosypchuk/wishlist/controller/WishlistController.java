package com.yosypchuk.wishlist.controller;

import com.yosypchuk.wishlist.api.WishlistApi;
import com.yosypchuk.wishlist.model.Wishlist;
import com.yosypchuk.wishlist.service.WishlistItemService;
import com.yosypchuk.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class WishlistController implements WishlistApi {

    private final WishlistService wishlistService;
    private final WishlistItemService wishlistItemService;

    @Override
    public Wishlist createWishlist(Long userId) {
        return wishlistService.createWishlist(userId);
    }

    @Override
    public Wishlist getWishlistByUserId(Long userId) {
        return wishlistService.getWishlistByUserId(userId);
    }

    @Override
    public ResponseEntity<Void> addProductToWishlist(Long userId, Long productId) {
        wishlistItemService.addWishlistItem(userId, productId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> removeProductFromWishlist(Long wishlistItemId) {
        wishlistItemService.removeWishlistItem(wishlistItemId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> removeAllProductsFromWishList(Long wishlistId) {
        wishlistItemService.removeAllWishlistItems(wishlistId);
        return ResponseEntity.noContent().build();
    }
}
