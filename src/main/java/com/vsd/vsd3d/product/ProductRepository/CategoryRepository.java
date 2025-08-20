package com.vsd.vsd3d.product.ProductRepository;

import com.vsd.vsd3d.product.ProductEntity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
