package com.roq.blogcrud.service;

import com.roq.blogcrud.dto.MessageResponse;
import com.roq.blogcrud.model.Post;
import com.roq.blogcrud.model.User;
import com.roq.blogcrud.repository.PostRepository;
import com.roq.blogcrud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPost(int id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElseThrow();
    }

    @Override
    public MessageResponse addPost(Post post, String token) {
        if (post == null)
            return MessageResponse.builder()
                    .code("400")
                    .message("Bad request")
                    .build();

        User author = author(token);
        System.out.println("User: "+ author);
        if (author != null)
            post.setUser(author);

        postRepository.save(post);
        return MessageResponse.builder()
                .code("201")
                .message(post.getTitle()+" post was created successfully!")
                .build();
    }

    @Override
    public MessageResponse deletePost(int id, String token) {
        boolean postAuthor = isPostAuthor(token);
        if (!postAuthor)
            return MessageResponse.builder()
                    .code("401")
                    .message("Unauthorized")
                    .build();

        Post post = getPost(id);
        if (post == null)
            return MessageResponse.builder()
                    .code("404")
                    .message("Post not found")
                    .build();

        postRepository.deleteById(id);
        return MessageResponse.builder()
                .code("200")
                .message(post.getTitle()+" has been deleted successfully!")
                .build();
    }

    @Override
    public MessageResponse updatePost(int id, Post post, String token) {
        boolean postAuthor = isPostAuthor(token);
        if (!postAuthor)
            return MessageResponse.builder()
                    .code("401")
                    .message("Unauthorized")
                    .build();

        Post post1 = getPost(id);
        if (post1 != null) {
            post.setId(id);
            post.setUser(post1.getUser());
            post.setDateTime(LocalDateTime.now());
            postRepository.save(post);
            return MessageResponse.builder()
                    .code("200")
                    .message(post.getTitle()+" has been updated successfully!")
                    .build();

        }
        return MessageResponse.builder()
                .code("400")
                .message(post.getTitle()+" post unable to update")
                .build();
    }

    public boolean isPostAuthor(String token) {
        User user = author(token);
        if (user == null) return false;
        List<Post> posts = postRepository.findByUserId(user.getId());
        return posts.size() > 0;
    }

    private User author(String token) {
        String username = jwtService.extractUsername(token);
        if (username != null) {
            Optional<User> optionalUser = userRepository.findByEmail(username);
            return optionalUser.orElse(null);
        }
        return null;
    }
}
