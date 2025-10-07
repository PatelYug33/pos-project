package com.ecom.pos.system.service.impl;


import com.ecom.pos.system.domain.UserRole;
import com.ecom.pos.system.exceptions.UserException;
import com.ecom.pos.system.mapper.CategoryMapper;
import com.ecom.pos.system.model.Category;
import com.ecom.pos.system.model.Store;
import com.ecom.pos.system.model.User;
import com.ecom.pos.system.payload.dto.CategoryDto;
import com.ecom.pos.system.repository.CategoryRepository;
import com.ecom.pos.system.repository.StoreRepository;
import com.ecom.pos.system.service.CategoryService;
import com.ecom.pos.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final StoreRepository storeRepository;

    @Override
    public CategoryDto createCategory(CategoryDto dto) throws Exception {
        User user = userService.getCurrentUser();
        Store store = storeRepository.findById(dto.getStoreId()).orElseThrow(()-> new Exception("store not found"));
        Category category= Category.builder()
                .store(store)
                .name(dto.getName())
                .build();

        checkAuthority(user,category.getStore());

        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> getCategoriesByStore(Long storeId) {
        List<Category> categories = categoryRepository.findByStoreId(storeId);
        return categories.stream().map(CategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long id,CategoryDto dto)  throws  Exception{
        Category category = categoryRepository.findById(id).orElseThrow(()->new Exception("category not exist"));
        User user= userService.getCurrentUser();
        category.setName(dto.getName());
        checkAuthority(user,category.getStore());
        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(()->new Exception("category not exist"));
        User user= userService.getCurrentUser();
        checkAuthority(user,category.getStore());
        categoryRepository.delete(category);
    }

    private void checkAuthority(User user,Store store) throws Exception {
        boolean isAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
        boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore = user.equals(store.getStoreAdmin());

        if(!(isAdmin && isSameStore) && !isManager){
            throw new Exception("you don't have permission to manage this category");
        }

    }
}
