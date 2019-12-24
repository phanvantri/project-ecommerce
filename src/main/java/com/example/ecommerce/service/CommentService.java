package com.example.ecommerce.service;

import com.example.ecommerce.domain.Comment;
import com.example.ecommerce.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findCommentByProduct(Long id){
        return commentRepository.findCommentByProduct(id);
    }

    public void SaveComment(Comment comment){
         commentRepository.save(comment);
    }
}
