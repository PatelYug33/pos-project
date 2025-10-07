package com.ecom.pos.system.service;

import com.ecom.pos.system.exceptions.UserException;
import com.ecom.pos.system.payload.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto dto) throws Exception;
    List<CategoryDto> getCategoriesByStore(Long storeId);
    CategoryDto updateCategory(Long id,CategoryDto dto) throws Exception;
    void deleteCategory(Long id) throws Exception;


}
