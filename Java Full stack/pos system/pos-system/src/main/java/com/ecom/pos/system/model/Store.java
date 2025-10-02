package com.ecom.pos.system.model;

import com.ecom.pos.system.domain.StoreStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @OneToOne
    private User storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;
    private String storeType;

    private StoreStatus status;
    @Embedded
    private StoreContact contact = new StoreContact();

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        status = StoreStatus.PENDDING;
    }
    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }
}
