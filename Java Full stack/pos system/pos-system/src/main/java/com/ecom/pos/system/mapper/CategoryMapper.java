package com.ecom.pos.system.mapper;


import com.ecom.pos.system.model.Category;
import com.ecom.pos.system.payload.dto.CategoryDto;

public class CategoryMapper {
    public static CategoryDto toDto(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .storeId(category.getStore() != null ? category.getStore().getId():null)
                .build();
    }

}
