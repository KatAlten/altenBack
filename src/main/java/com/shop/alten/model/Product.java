package com.shop.alten.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "products")
public class Product {

    public enum InventoryStatus {
        INSTOCK,
        LOWSTOCK,
        OUTOFSTOCK
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    private String description;

    private String image;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "internal_reference", nullable = false)
    private String internalReference;

    @Column(name = "shell_id", nullable = false)
    private Long shellId;

    @Enumerated(EnumType.STRING)
    @Column(name = "inventory_status", nullable = false)
    private InventoryStatus inventoryStatus;

    private Double rating;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;


}
