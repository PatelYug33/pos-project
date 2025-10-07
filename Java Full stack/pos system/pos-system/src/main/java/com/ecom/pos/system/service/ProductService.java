package com.ecom.pos.system.service;

import com.ecom.pos.system.model.Product;
import com.ecom.pos.system.model.User;
import com.ecom.pos.system.payload.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto, User user) throws Exception;
    ProductDto updateProduct(Long id,ProductDto productDto, User user) throws Exception;
    void deleteProduct(Long id,User user) throws Exception;
    List<ProductDto> getProductsByStoreId(Long storeId);
    List<ProductDto> searchByKeyword(Long storeId,String keyword);



}
