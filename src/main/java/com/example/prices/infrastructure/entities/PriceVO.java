package com.example.prices.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PriceVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "brand_id", nullable = false)
    private Integer brandId;

    @Column(name = "price_list", nullable = false)
    private Integer priceList;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "priority", nullable = false)
    private Integer priority;

    @Column(name = "curr", nullable = false)
    private String currency;

}
