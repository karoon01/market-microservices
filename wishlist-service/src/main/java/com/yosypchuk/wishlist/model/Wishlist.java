package com.yosypchuk.wishlist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_wishlist")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "wishlist")
    @JsonIgnoreProperties("wishlist")
    private List<WishlistItem> wishlistItems;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
