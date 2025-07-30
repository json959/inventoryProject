package com.inventory.purchase.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "purchase")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private Long quantityPurchased;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datePurchase;

    @PrePersist
    protected void onCreate() {
        this.datePurchase = new Date();
    }
}
