package com.roq.blogcrud.controller;

import com.roq.blogcrud.dto.MessageResponse;
import com.roq.blogcrud.exception.NotFoundException;
import com.roq.blogcrud.model.Post;
import com.roq.blogcrud.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("view")
    public ResponseEntity<List<Post>> posts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("view/{id}")
    public ResponseEntity<Post> post(@PathVariable int id) throws NotFoundException {
        Post post = postService.getPost(id);
        if (post == null) throw new NotFoundException("Post with "+id + "not found");
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> addPost(
            @Valid @RequestBody Post post,
            @RequestHeader(value = "Authorization") String header) {
        String token = header.substring(7);
        return ResponseEntity.ok(postService.addPost(post, token));
    }

    @PutMapping("{id}")
    public ResponseEntity<MessageResponse> updatePost(
            @Valid @RequestBody Post post,
            @PathVariable int id,
            @RequestHeader(value = "Authorization") String header) {
        String token = header.substring(7);
        return ResponseEntity.ok(postService.updatePost(id, post, token));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MessageResponse> deletePost(
            @PathVariable int id,
            @RequestHeader(value = "Authorization") String header) {
        String token = header.substring(7);
        return ResponseEntity.ok(postService.deletePost(id, token));
    }
}
