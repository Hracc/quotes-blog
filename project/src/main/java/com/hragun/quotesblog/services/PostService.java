package com.hragun.quotesblog.services;

import com.hragun.quotesblog.DTO.PostDTO;
import com.hragun.quotesblog.models.Post;
import com.hragun.quotesblog.models.User;
import com.hragun.quotesblog.repository.PostRepository;
import com.hragun.quotesblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Iterable<PostDTO> getAllPosts() {
        List<PostDTO> postDtos = new ArrayList<>();
        Iterable<Post> posts = postRepository.findAllPosts();
        createPost(postDtos, posts);
        return postDtos;
    }
    public Iterable<PostDTO> getPublishedPosts() {
        List<PostDTO> postDtos = new ArrayList<>();
        Iterable<Post> posts = postRepository.findPublishedPosts();
        createPost(postDtos, posts);
        return postDtos;
    }

    public Iterable<PostDTO> getAllPosts(Long idUser) {
        List<PostDTO> postDtos = new ArrayList<>();
        Iterable<Post> posts = postRepository.findPost(idUser);
        createPost(postDtos, posts);
        return postDtos;
    }

    private void createPost(List<PostDTO> postDtos, Iterable<Post> posts) {
        for (Post post : posts) {
            User user = userRepository.findById(post.getIdUser()).orElse(null);
            PostDTO postDto = new PostDTO();
            postDto.setIdPost(post.getIdPost());
            postDto.setIdUser(post.getIdUser());
            postDto.setUsername(user != null ? user.getUsername() : null);
            postDto.setTextPost(post.getTextPost());
            postDto.setStatusPost(post.getStatusPost());
            postDtos.add(postDto);
        }
    }
}
