package com.roq.blogcrud;

import com.roq.blogcrud.model.Post;
import com.roq.blogcrud.model.User;
import com.roq.blogcrud.repository.PostRepository;
import com.roq.blogcrud.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
@Slf4j
public class BlogCrudApplication implements CommandLineRunner {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BlogCrudApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setEmail("tester@test.com");
        user.setPassword(passwordEncoder.encode("password"));
        user.setLastName("tester");
        user.setFirstName("user");
        User user1 = userRepository.save(user);

        log.info("{} user's account has been created successfully", user1.getFirstName());

        Post post = new Post();
        post.setTitle("First Post");
        post.setDescription("Latest post description");
        post.setDateTime(LocalDateTime.now());
        post.setUser(user1);
        postRepository.save(post);
        log.info("{} post has been created successfully", post.getTitle());

    }
}
