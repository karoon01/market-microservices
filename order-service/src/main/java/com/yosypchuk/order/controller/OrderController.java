package com.yosypchuk.order.controller;

import com.yosypchuk.order.api.OrderApi;
import com.yosypchuk.order.model.Order;
import com.yosypchuk.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController implements OrderApi {

    private final OrderService orderService;

    @Override
    public Order createOrder(Long userId) {
        return orderService.createOrder(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Override
    public List<Order> getAllUserOrders(Long userId) {
        return orderService.getAllUserOrders(userId);
    }

    @Override
    public ResponseEntity<Void> deleteOrderItemFromOrder(Long orderItemId) {
        orderService.deleteOrderItemFromOrder(orderItemId);
        return ResponseEntity.noContent().build();
    }
}
