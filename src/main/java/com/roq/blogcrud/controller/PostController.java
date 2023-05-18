package com.roq.blogcrud.controller;

import com.roq.blogcrud.dto.MessageResponse;
import com.roq.blogcrud.model.Post;
import com.roq.blogcrud.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("view/posts")
    public ResponseEntity<List<Post>> posts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("view/posts/{id}")
    public ResponseEntity<Post> post(@PathVariable int id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @PostMapping("posts")
    public ResponseEntity<MessageResponse> addPost(
            @RequestBody Post post,
            @RequestHeader(value = "Authorization") String header) {
        String token = header.substring(7);
        return ResponseEntity.ok(postService.addPost(post, token));
    }

    @PutMapping("posts/{id}")
    public ResponseEntity<MessageResponse> updatePost(
            @RequestBody Post post,
            @PathVariable int id,
            @RequestHeader(value = "Authorization") String header) {
        String token = header.substring(7);
        return ResponseEntity.ok(postService.updatePost(id, post, token));
    }

    @DeleteMapping("posts/{id}")
    public ResponseEntity<MessageResponse> deletePost(
            @PathVariable int id,
            @RequestHeader(value = "Authorization") String header) {
        String token = header.substring(7);
        return ResponseEntity.ok(postService.deletePost(id, token));
    }
}
