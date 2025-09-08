package com.vsd.vsd3d.product.ProductDto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String sku;
    private Long categoryId;
    private String categoryName;
    private String description;
    private String unit;
}
