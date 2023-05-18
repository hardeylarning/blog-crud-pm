package com.roq.blogcrud.repository;

import com.roq.blogcrud.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {


    List<Post> findByUserId(Integer userId);
}