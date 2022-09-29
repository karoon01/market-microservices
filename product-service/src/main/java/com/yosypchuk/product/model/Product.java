package com.yosypchuk.product.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name= "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "product_category_id")
    )
    @JsonIgnoreProperties("products")
    private List<ProductCategory> categories;

    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties("product")
    private List<ProductReview> reviews;

    @Transient
    private Double averageRate;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "amount", nullable = false)
    private Integer amount;
}
