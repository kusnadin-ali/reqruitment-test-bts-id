package com.bts.product_service.service;

import com.bts.product_service.dto.CommonResponse;
import com.bts.product_service.dto.ProductDTO;
import com.bts.product_service.repository.ProductRepository;
import com.bts.product_service.utils.ResponseUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bts.product_service.model.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    @Override
    public ResponseEntity<CommonResponse> getAllProducts(String search, String category, Integer limit, Integer page) {
        
        int pageNumber = (page != null && page > 0) ? page - 1 : 0;
        int pageSize = (limit != null && limit > 0) ? limit : 10;

        String searchParam = (search != null && search.trim().isEmpty()) ? null : search;
        String categoryParam = (category != null && category.trim().isEmpty()) ? null : category;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> productPage = productRepository.findProductsWithFilters(searchParam, categoryParam, pageable);

        Map<String, Object> paginationData = new HashMap<>();
        paginationData.put("items", productPage.getContent());
        paginationData.put("total_items", productPage.getTotalElements());
        paginationData.put("total_pages", productPage.getTotalPages());
        paginationData.put("current_page", productPage.getNumber() + 1);

        return ResponseUtil.success("Success Retrieve All Products", paginationData);
    }

    @Override
    public ResponseEntity<CommonResponse> getProductById(Long id) {

        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseUtil.failedWithStatus(HttpStatus.NOT_FOUND, "Product Not Found");
        }
        return ResponseUtil.success("Success Retrieve Product", productOptional.get());
    }

    @Override
    public ResponseEntity<CommonResponse> createNewProduct(ProductDTO request) {


        Product product = mapToEntity(request);
        productRepository.save(product);

        return ResponseUtil.success("Success Create New Product", product);
    }

    @Override
    public ResponseEntity<CommonResponse> deleteProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseUtil.failedWithStatus(HttpStatus.NOT_FOUND, "Product Not Found");
        }
        productRepository.deleteById(id);

        return ResponseUtil.success("Success Delete Product", productOptional.get());
    }

    @Override
    public ResponseEntity<CommonResponse> updateProductById(Long id, ProductDTO request) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            return ResponseUtil.failedWithStatus(HttpStatus.NOT_FOUND, "Product Not Found");
        }

        Product product = mapToEntity(request);
        product.setId(id);
        productRepository.save(product);

        return ResponseUtil.success("Success Update Product", product);
    }

    private Product mapToEntity(ProductDTO dto) {

        return Product.builder()
                .title(dto.getTitle())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .images(dto.getImages())
                .createdBy(dto.getCreated_by())
                .createdById(dto.getCreated_by_id())
                .updatedBy(dto.getUpdated_by())
                .updatedById(dto.getUpdated_by_id())
                .build();
    }
}
