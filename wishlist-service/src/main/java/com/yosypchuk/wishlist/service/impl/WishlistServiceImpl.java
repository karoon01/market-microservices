package com.yosypchuk.wishlist.service.impl;

import com.yosypchuk.wishlist.exception.EntityAlreadyExistException;
import com.yosypchuk.wishlist.exception.EntityNotFoundException;
import com.yosypchuk.wishlist.model.Wishlist;
import com.yosypchuk.wishlist.repository.WishlistRepository;
import com.yosypchuk.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;

    @Transactional
    @Override
    public Wishlist createWishlist(Long userId) {
        log.info("Trying to get wishlist for user with id: {}", userId);
        Optional<Wishlist> possibleWishlist = wishlistRepository.findWishlistByUserId(userId);

        if(possibleWishlist.isPresent()) {
            log.error("Wishlist for user with id: {} is already exist", userId);
            throw new EntityAlreadyExistException("Wishlist for user is already exist!");
        }

        Wishlist wishlist = Wishlist.builder()
                .userId(userId)
                .wishlistItems(Collections.emptyList())
                .build();

        log.info("Create wishlist for user with id: {}", userId);
        wishlistRepository.save(wishlist);

        return wishlist;
    }

    @Override
    public Wishlist getWishlistByUserId(Long userId) {
        log.info("Get wishlist for user with id: {}", userId);
        return wishlistRepository.findWishlistByUserId(userId)
                .orElse(null);
    }

    @Override
    public Wishlist getWishlistById(Long wishlistId) {
        log.info("Get wishlist by id: {}", wishlistId);
        return wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new EntityNotFoundException("Wishlist doesn't exist!"));
    }
}
