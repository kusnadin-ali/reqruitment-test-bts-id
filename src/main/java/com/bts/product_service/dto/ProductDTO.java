package com.bts.product_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductDTO {
    @NotEmpty(message = "this field cant be empty")
    @NotNull(message = "this field cant be null")
    private String title;

    @NotNull(message = "this field cant be null")
    private BigDecimal price;

    private String description;

    @NotEmpty(message = "this field cant be empty")
    @NotNull(message = "this field cant be null")
    private String category;

    @NotEmpty(message = "images list cannot be empty, please provide at least 1 image")
    private List<String> images;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created_at;
    
    private String created_by;
    private String created_by_id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated_at;
    
    private String updated_by;
    private String updated_by_id;
}
