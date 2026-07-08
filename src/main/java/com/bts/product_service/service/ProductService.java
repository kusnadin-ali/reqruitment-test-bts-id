package com.bts.product_service.service;


import com.bts.product_service.dto.CommonResponse;
import com.bts.product_service.dto.ProductDTO;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    ResponseEntity<CommonResponse> getAllProducts(String search, String category, Integer limit, Integer page);
    ResponseEntity<CommonResponse> getProductById(Long id);

    ResponseEntity<CommonResponse> createNewProduct(ProductDTO request);

    ResponseEntity<CommonResponse> deleteProductById(Long id);

    ResponseEntity<CommonResponse> updateProductById(Long id, ProductDTO request);
}
