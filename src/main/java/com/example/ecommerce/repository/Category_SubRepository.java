package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Category_Sub;
import com.example.ecommerce.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Category_SubRepository  extends JpaRepository<Category_Sub,Long> {
    @Query("select e from Category_Sub e where e.category.id=?1")
    List<Category_Sub> findAllByCategoryById(Long id);
    @Query("select e.lstProduct from Category_Sub e where e.category.id=?1")
    Page<Product> findProductOfCategory(Long id, Pageable pageable);
    @Query("select e.lstProduct from Category_Sub e where e.category.id=?1")
    List<Product> findProductOfCategoryTest(Long id);
    @Query("select e from Category_Sub e where e.category.id=?1")
    List<Category_Sub> findCategory_SubByCategory(Long id);

    Category_Sub findOneById(Long id);
}
