package com.yosypchuk.wishlist.service.impl;

import com.yosypchuk.wishlist.exception.EntityAlreadyExistException;
import com.yosypchuk.wishlist.exception.EntityNotFoundException;
import com.yosypchuk.wishlist.model.Wishlist;
import com.yosypchuk.wishlist.model.WishlistItem;
import com.yosypchuk.wishlist.model.dto.WishlistItemRequest;
import com.yosypchuk.wishlist.repository.WishlistItemRepository;
import com.yosypchuk.wishlist.service.WishlistItemService;
import com.yosypchuk.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class WishlistItemServiceImpl implements WishlistItemService {

    private final WishlistItemRepository wishlistItemRepository;
    private final WishlistService wishlistService;

    @Transactional
    @Override
    public void addWishlistItem(Long userId, WishlistItemRequest wishlistItemRequest) {
        Long productId = wishlistItemRequest.getProductId();

        log.info("Get wishlist by user id: {}", userId);
        Wishlist wishlist = wishlistService.getWishlistByUserId(userId);

        if(wishlist == null) {
            log.info("Create a wishlist for user with id: {}", userId);
            wishlistService.createWishlist(userId);

            wishlist = wishlistService.getWishlistByUserId(userId);
        }

        if(wishlist.getWishlistItems() != null) {
            for(WishlistItem item : wishlist.getWishlistItems()) {
                if(item.getProductId().equals(productId)) {
                    log.info("Wishlist item with product with id: {} is already exist", productId);
                    throw new EntityAlreadyExistException("Wishlist item is already exist!");
                }
            }
        }

        WishlistItem wishlistItem = WishlistItem.builder()
                .wishlist(wishlist)
                .productId(productId)
                .build();

        log.info("Save wishlist item to database");
        wishlistItemRepository.save(wishlistItem);
    }

    @Override
    public WishlistItem getWishlistItemById(Long id) {
        log.info("Get wishlist item by id: {}", id);
        WishlistItem wishlistItem = wishlistItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Wishlist item doesn't exist!"));

        return wishlistItem;
    }

    @Transactional
    @Override
    public void removeWishlistItem(Long wishlistItemId) {
        log.info("Get wishlist item by id: {}", wishlistItemId);
        WishlistItem wishlistItem = wishlistItemRepository.findById(wishlistItemId)
                .orElseThrow(() -> new EntityNotFoundException("Wishlist item doesn't exist!"));

        log.info("Delete wishlist item by id: {}", wishlistItemId);
        wishlistItemRepository.delete(wishlistItem);
    }

    @Override
    public void removeAllWishlistItems(Long wishlistId) {
        log.info("Get wishlist by id: {}", wishlistId);
        wishlistItemRepository.findById(wishlistId)
                        .orElseThrow(() -> new EntityNotFoundException("Wishlist doesn't exist!"));

        log.info("Remove all wishlist items from wishlist with id: {}", wishlistId);
        wishlistItemRepository.removeAllWishlistItemsFromWishlist(wishlistId);
    }
}
