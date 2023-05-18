package com.roq.blogcrud.service;

import com.roq.blogcrud.dto.MessageResponse;
import com.roq.blogcrud.model.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPost(int id);

    MessageResponse addPost(Post post, String token);
    MessageResponse deletePost(int id, String token);

    MessageResponse updatePost(int id, Post post, String token);

}
