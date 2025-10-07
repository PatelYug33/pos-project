package com.ecom.pos.system.controller;

import com.ecom.pos.system.payload.dto.CategoryDto;
import com.ecom.pos.system.payload.response.ApiResponse;
import com.ecom.pos.system.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) throws Exception {
        return ResponseEntity.ok(categoryService.createCategory(categoryDto));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<CategoryDto>> getCategoriesByStoreId(@PathVariable Long storeId) throws Exception {
        return ResponseEntity.ok(categoryService.getCategoriesByStore(storeId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(categoryService.updateCategory(id,categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@RequestBody CategoryDto categoryDto, @PathVariable Long id) throws Exception {
        categoryService.updateCategory(id,categoryDto);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMassage("category delete Successfully");

        return ResponseEntity.ok(apiResponse);
    }
}
