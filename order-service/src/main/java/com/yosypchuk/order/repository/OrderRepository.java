package com.yosypchuk.order.repository;

import com.yosypchuk.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.userId=?1")
    List<Order> getAllOrdersByUserId(Long userId);
}
