package com.vsd.vsd3d.product.ProductRepository;

import com.vsd.vsd3d.product.ProductEntity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existBySku(String sku);
}
