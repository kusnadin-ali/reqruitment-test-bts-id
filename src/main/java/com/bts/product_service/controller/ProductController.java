package com.bts.product_service.controller;

import com.bts.product_service.dto.CommonResponse;
import com.bts.product_service.dto.ProductDTO;
import com.bts.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<CommonResponse> getAllProducts(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        return productService.getAllProducts(search, category, limit, page);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getProductById(@PathVariable Long id) {
        return  productService.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<CommonResponse> createProduct(@RequestBody @Valid ProductDTO request) {
        return productService.createNewProduct(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productService.updateProductById(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteProduct(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }
}
