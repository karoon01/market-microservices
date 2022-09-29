package com.yosypchuk.product.repository;

import com.yosypchuk.product.model.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

    @Query("SELECT pr FROM ProductReview pr WHERE pr.userId=?1 AND pr.product.id=?2")
    Optional<ProductReview> findProductRateByUserIdAndProductId(Long userId, Long productId);

    @Query("SELECT pr FROM ProductReview pr WHERE pr.product.id=?1")
    List<ProductReview> getAllByProductId(Long productId);
}
