package com.example.ecommerce.web.rest;

import com.example.ecommerce.domain.Comment;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.security.CurrentUser;
import com.example.ecommerce.security.UserPrincipal;
import com.example.ecommerce.service.CommentService;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.UserService;
import com.example.ecommerce.service.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/v1")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;
    @GetMapping("/getCommentByProduct")
    public ResponseEntity<?> findCommentByProduct(@RequestParam String id){
        List<Comment> lst=commentService.findCommentByProduct(Long.parseLong(id));
        List<CommentDTO> lstComment=new ArrayList<>();
        for(Comment s:lst){
            CommentDTO c=new CommentDTO();
            c.setContent(s.getContent());
            c.setAuthor(s.getUser().getName());
           // c.setAvatar("https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png");
            c.setAvatar(s.getUser().getImageUrl());
            lstComment.add(c);
        }



        return new ResponseEntity<>(lstComment, HttpStatus.OK);
    }
    @PostMapping("/addcomment")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> AddUser(@RequestBody CommentDTO commentDTO, @CurrentUser UserPrincipal userPrincipal){
        try {
            Date date=new Date();
        User user=userService.findById(userPrincipal.getId());
        Product product=productService.findById(Long.parseLong(commentDTO.getIdProduct()));
       Comment comment=new Comment();
       comment.setContent(commentDTO.getContent());
       comment.setUser(user);
       comment.setProduct(product);
       comment.setDatecomment(date);

       commentService.SaveComment(comment);
       return  new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }




    }

}
