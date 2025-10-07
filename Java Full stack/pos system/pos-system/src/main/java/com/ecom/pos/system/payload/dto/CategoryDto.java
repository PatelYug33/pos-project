package com.ecom.pos.system.payload.dto;


import com.ecom.pos.system.model.Store;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryDto {

    private Long id;

    private String name;

//    private Store store;

    private Long storeId;
}
