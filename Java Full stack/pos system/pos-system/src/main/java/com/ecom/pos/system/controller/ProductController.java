package com.ecom.pos.system.controller;

import com.ecom.pos.system.model.User;
import com.ecom.pos.system.payload.dto.ProductDto;
import com.ecom.pos.system.payload.response.ApiResponse;
import com.ecom.pos.system.service.ProductService;
import com.ecom.pos.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto, @RequestHeader("Authorization")String jwt) throws Exception {
        User user= userService.getUserFromJwtToken(jwt);

        return ResponseEntity.ok(productService.createProduct(productDto,user));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<ProductDto>>   getByStoreId(@PathVariable Long storeId, @RequestHeader("Authorization")String jwt) throws Exception {
        return ResponseEntity.ok(productService.getProductsByStoreId(storeId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id,@RequestBody ProductDto productDto, @RequestHeader("Authorization")String jwt) throws Exception {
        User user= userService.getUserFromJwtToken(jwt);

        return ResponseEntity.ok(productService.updateProduct(id,productDto, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id, @RequestHeader("Authorization")String jwt) throws Exception {
        User user= userService.getUserFromJwtToken(jwt);
        productService.deleteProduct(id,user);

        ApiResponse apiResponse= new ApiResponse();
        apiResponse.setMassage("product delect successfully");

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/store/{storeId}/search")
    public ResponseEntity<List<ProductDto>>  searchByKeyword(@PathVariable Long storeId,@RequestParam String keyword ,@RequestHeader("Authorization")String jwt) throws Exception {
        return ResponseEntity.ok(productService.searchByKeyword(storeId,keyword));
    }

}
