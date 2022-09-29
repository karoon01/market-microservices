package com.yosypchuk.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_review")
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("reviews")
    @JsonIgnore
    private Product product;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "rate", nullable = false)
    private Integer rate;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "pros", nullable = false)
    private String pros;

    @Column(name = "cons", nullable = false)
    private String cons;
}