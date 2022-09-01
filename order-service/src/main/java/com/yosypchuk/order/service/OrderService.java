package com.yosypchuk.order.service;


import com.yosypchuk.order.model.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Long userId);

    List<Order> getAllOrders();

    List<Order> getAllUserOrders(Long userId);

    void deleteOrderItemFromOrder(Long orderItemId);
}
