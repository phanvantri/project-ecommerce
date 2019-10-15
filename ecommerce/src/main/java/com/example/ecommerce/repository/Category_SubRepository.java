package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Category_Sub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Category_SubRepository  extends JpaRepository<Category_Sub,Long> {
    @Query("select e from Category_Sub e where e.category.id=?1")
    List<Category_Sub> findAllByCategoryById(Long id);

    Category_Sub findOneById(Long id);
}
