package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("select e from Comment e where e.product.id=?1 order by e.datecomment asc ")
    List<Comment> findCommentByProduct(Long id);
}
