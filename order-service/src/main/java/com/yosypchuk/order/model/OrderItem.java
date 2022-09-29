package com.yosypchuk.order.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_order_id")
    @JsonIgnoreProperties("orderItems")
    private Order order;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "total_order_item_price", nullable = false)
    private Double totalOrderItemPrice;
}
