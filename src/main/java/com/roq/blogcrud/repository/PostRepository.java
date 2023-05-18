package com.roq.blogcrud.repository;

import com.roq.blogcrud.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
