package com.yosypchuk.wishlist.repository;

import com.yosypchuk.wishlist.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    @Query("SELECT wl FROM Wishlist wl WHERE wl.userId=?1")
    Optional<Wishlist> findWishlistByUserId(Long userId);
}
