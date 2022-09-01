package com.yosypchuk.order.service.impl;

import com.yosypchuk.order.exception.EntityNotFoundException;
import com.yosypchuk.order.model.Cart;
import com.yosypchuk.order.model.Order;
import com.yosypchuk.order.model.OrderItem;
import com.yosypchuk.order.repository.OrderItemRepository;
import com.yosypchuk.order.repository.OrderRepository;
import com.yosypchuk.order.service.CartService;
import com.yosypchuk.order.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartService cartService;

    @Transactional
    @Override
    public Order createOrder(Long userId) {
        log.info("Get cart by user id: {}", userId);
        Cart cart = cartService.getCartByUserId(userId);

        Order order = new Order();

        log.info("Get all orderItem's from cart");
        List<OrderItem> orderItems = cart.getCartItems()
                .stream()
                .map(cartItem -> {
                    OrderItem orderItem = OrderItem.builder()
                            .order(order)
                            .productId(cartItem.getProductId())
                            .amount(cartItem.getAmount())
                            .totalOrderItemPrice(cartItem.getPrice())
                            .build();
                    return orderItemRepository.save(orderItem);
                })
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);
        order.setTotalOrderPrice(cart.getTotalPrice());

        log.info("Save order");
        orderRepository.save(order);

        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        log.info("Get all user's orders");
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllUserOrders(Long userId) {
        log.info("Get all orders for user with id: {}", userId);
        return orderRepository.getAllOrdersByUserId(userId);
    }

    @Override
    public void deleteOrderItemFromOrder(Long orderItemId) {
        log.info("Get orderItem by id: {}", orderItemId);
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new EntityNotFoundException("OrderItem doesn't exist!"));

        log.info("Delete orderItem by id: {}", orderItemId);
        orderItemRepository.delete(orderItem);
    }
}
