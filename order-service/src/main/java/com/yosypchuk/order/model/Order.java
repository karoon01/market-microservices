package com.yosypchuk.order.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yosypchuk.order.model.constatnts.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="user_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order")
    @JsonIgnoreProperties("order")
    private List<OrderItem> orderItems;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @Column(name = "total_order_price", nullable = false)
    private Double totalOrderPrice;
}
