package com.roq.blogcrud;

import com.roq.blogcrud.model.Post;
import com.roq.blogcrud.repository.PostRepository;
import com.roq.blogcrud.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PostServiceTest extends BlogCrudApplicationTests {

    @Mock
    PostRepository postRepository;
    @Autowired
    @Spy
    private PostService postService;



    @BeforeEach
    void init() {
        Post post = new Post();
        post.setId(1);
        post.setTitle("Updated Post overriden");
        post.setDescription("check if post pass test");
        post.setDateTime(LocalDateTime.now());

        Mockito.when(postRepository.getById(1)).thenReturn(post);


    }

    @Test
    void testGetPostById() {
        String postTitle = "Updated Post overriden";
        Post post = postService.getPost(1);
        assertEquals(post.getTitle(), postTitle);
    }

    @Test
    void testGetAllPosts() {
        List<Post> posts = postService.getAllPosts();
        assertTrue(posts.size() > 0);
    }
}
