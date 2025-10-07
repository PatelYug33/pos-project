package com.ecom.pos.system.service.impl;

import com.ecom.pos.system.mapper.ProductMapper;
import com.ecom.pos.system.model.Product;
import com.ecom.pos.system.model.Store;
import com.ecom.pos.system.model.User;
import com.ecom.pos.system.payload.dto.ProductDto;
import com.ecom.pos.system.repository.ProductRepository;
import com.ecom.pos.system.repository.StoreRepository;
import com.ecom.pos.system.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;


    @Override
    public ProductDto createProduct(ProductDto productDto, User user) throws Exception {
        Store store= storeRepository.findById(productDto.getStoreId()).orElseThrow(()->new Exception("Store not found !"));
        Product product = ProductMapper.toEntity(productDto,store);
        Product savedProduct=productRepository.save(product);

        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto, User user) throws Exception {
        Product product =productRepository.findById(id).orElseThrow(()->new Exception("Product not found"));
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setSku(productDto.getSku());
        product.setImage(productDto.getImage());
        product.setMrp(productDto.getMrp());
        product.setSellingPrice(productDto.getSellingPrice());
        product.setBrand(productDto.getBrand());
        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct= productRepository.save(product);
        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public void deleteProduct(Long id, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(()->new Exception("product not found"));
        productRepository.delete(product);
    }

    @Override
    public List<ProductDto> getProductsByStoreId(Long storeId) {
        List<Product> products = productRepository.findByStoreId(storeId);
        return products.stream().
                map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchByKeyword(Long storeId, String keyword) {
        List<Product> products = productRepository.searchByKeyword(storeId,keyword);
        return products.stream().
                map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }
}
