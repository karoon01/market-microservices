package com.yosypchuk.product.repository;

import com.yosypchuk.product.model.Product;
import com.yosypchuk.product.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    Optional<ProductCategory> findProductCategoryByName(String name);

    @Modifying
    @Query(value = "INSERT INTO product_categories (product_id, product_category_id) VALUES (:productId, :categoryId)",
            nativeQuery = true)
    @Transactional
    void addProductCategory(@Param("productId") Long productId,
                              @Param("categoryId") Long categoryId);

    @Modifying
    @Query(value = "DELETE FROM product_categories WHERE product_id=:productId AND product_category_id=:categoryId",
    nativeQuery = true)
    @Transactional
    void removeProductCategory(@Param("productId") Long productId,
                               @Param("categoryId")Long categoryId);

    @Query(value = "SELECT c.products FROM ProductCategory c WHERE c.id=?1")
    @Transactional
    List<Product> findAllProductsByCategory(Long categoryId);
}
