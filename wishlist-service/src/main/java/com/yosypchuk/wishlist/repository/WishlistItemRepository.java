package com.yosypchuk.wishlist.repository;

import com.yosypchuk.wishlist.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {

    @Modifying
    @Query("DELETE FROM WishlistItem wl WHERE wl.id=?1")
    @Transactional
    void removeAllWishlistItemsFromWishlist(Long wishlistId);
}
