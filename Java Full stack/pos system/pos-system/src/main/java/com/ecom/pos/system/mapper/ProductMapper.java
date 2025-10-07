package com.ecom.pos.system.mapper;

import com.ecom.pos.system.model.Category;
import com.ecom.pos.system.model.Product;
import com.ecom.pos.system.model.Store;
import com.ecom.pos.system.payload.dto.ProductDto;

public class ProductMapper {

    public static ProductDto toDto(Product product){
        return  ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .brand(product.getBrand())
                .category(CategoryMapper.toDto(product.getCategory()))
                .storeId(product.getStore() != null ? product.getStore().getId():null)
                .image(product.getImage())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();

    }

    public static Product toEntity(ProductDto productDto, Store store, Category category){
        return Product.builder()
                .name(productDto.getName())
                .store(store)
                .category(category)
                .sku(productDto.getSku())
                .description(productDto.getDescription())
                .mrp(productDto.getMrp())
                .sellingPrice(productDto.getSellingPrice())
                .brand(store.getBrand())
                .build();
    }

}
