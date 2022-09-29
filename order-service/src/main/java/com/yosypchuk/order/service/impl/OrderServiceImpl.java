package com.yosypchuk.order.service.impl;

import com.yosypchuk.order.exception.EntityNotFoundException;
import com.yosypchuk.order.model.Cart;
import com.yosypchuk.order.model.Order;
import com.yosypchuk.order.model.OrderItem;
import com.yosypchuk.order.model.constatnts.OrderStatus;
import com.yosypchuk.order.model.dto.ProductDTO;
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
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartService cartService;
    private final RestTemplate restTemplate;

    private final static String GET_PRODUCT_URL = "http://localhost:8083/product/{productId}";

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
        order.setStatus(OrderStatus.PENDING);
        order.setUserId(userId);

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

    @Transactional
    @Override
    public void deleteOrderItemFromOrder(Long orderItemId) {
        log.info("Get orderItem by id: {}", orderItemId);
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new EntityNotFoundException("OrderItem doesn't exist!"));

        log.info("Delete orderItem by id: {}", orderItemId);
        orderItemRepository.delete(orderItem);

        Long orderId = orderItem.getOrder().getId();

        log.info("Get order by id: {}", orderId);
        Order order = orderRepository.findById(orderId)
                        .orElseThrow(() -> new EntityNotFoundException("Order doesn't exist!"));

        updateOrderTotalPrice(order);
    }

    @Override
    public void addOrderItemToOrder(Long orderId, Long productId, Integer amount) {
        log.info("Get order by id: {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order doesn't exist!"));

        ProductDTO product = restTemplate.getForObject(GET_PRODUCT_URL, ProductDTO.class, productId);

        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .productId(productId)
                .totalOrderItemPrice(product.getPrice())
                .amount(amount)
                .build();

        log.info("Save orderItem to database");
        orderItemRepository.save(orderItem);

        updateOrderTotalPrice(order);
    }

    private Order updateOrderTotalPrice(Order order) {
        Long orderId = order.getId();

        log.info("Get all order items from order with id: {}", orderId);
        List<OrderItem> orderItems = orderItemRepository.findAllItemsByOrderId(orderId);

        Double totalPrice = orderItems.stream()
                .map(OrderItem::getTotalOrderItemPrice)
                .reduce(0.0, Double::sum);

        order.setTotalOrderPrice(totalPrice);

        return order;
    }
}
