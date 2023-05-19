package com.roq.blogcrud;

import com.roq.blogcrud.model.Post;
import com.roq.blogcrud.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PostRepository postRepository;


    @BeforeEach
    void init() {
        Post post = new Post();
        post.setTitle("test20");
        post.setDescription("check if post pass test");
        post.setDateTime(LocalDateTime.now());
        testEntityManager.persist(post);
    }

    @Test
    void testGetPostById() {
        int id = 1;
        Post post = postRepository.findById(1).get();
        assertEquals(post.getId(), id);
    }

    @Test
    void testGetPosts() {
        assertTrue(postRepository.findAll().size() > 0);
    }

    @Test
    void testAddPost() {
        String title = "test20";
        assertEquals(postRepository.findByTitle("test20").getTitle(), title);
    }

    @Test
    void testUpdatePost() {
        Post post = postRepository.findByTitle("test20");
        post.setTitle("test25");
        postRepository.save(post);
        assertEquals(postRepository.findByTitle("test25").getTitle(), "test25");
    }

    @Test
    void testDeletePost() {
        Post post = new Post();
        post.setTitle("test21");
        post.setDescription("check if post pass test");
        post.setDateTime(LocalDateTime.now());
        final int id = testEntityManager.persistAndGetId(post, Integer.class);
        postRepository.deleteById(id);
        testEntityManager.flush();
        Post after = testEntityManager.find(Post.class, id);
        assertNull(after);
    }
}
