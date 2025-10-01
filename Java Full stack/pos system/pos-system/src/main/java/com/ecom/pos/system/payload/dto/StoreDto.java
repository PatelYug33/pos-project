package com.ecom.pos.system.payload.dto;

import com.ecom.pos.system.domain.StoreStatus;
import com.ecom.pos.system.model.StoreContact;
import com.ecom.pos.system.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoreDto {

    private Long id;

    private String brand;

    private User storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;
    private String storeType;

    private StoreStatus status;
    private StoreContact contact;
}
