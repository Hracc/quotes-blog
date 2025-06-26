package com.hragun.quotesblog.controllers;

import com.hragun.quotesblog.DTO.PostCreateDTO;
import com.hragun.quotesblog.DTO.PostDTO;
import com.hragun.quotesblog.models.Post;
import com.hragun.quotesblog.repository.PostRepository;
import com.hragun.quotesblog.repository.UserRepository;
import com.hragun.quotesblog.services.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PostRestController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    private HttpSession session;

    @Autowired
    public PostRestController(PostService postService, PostRepository postRepository, UserRepository userRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    //Вывод в профиле
    @GetMapping("/fetch/user/{idUser}")
    public Iterable<PostDTO> getProfilePost(@PathVariable Long idUser) {
        return postService.getAllPosts(idUser);
    }

    @PostMapping("/fetch/post/create")
    public ResponseEntity<String> createPost(@RequestBody PostCreateDTO postDTO) {
        try {
            Post post = new Post(userRepository.findIdUserByNick(postDTO.getUsername()), postDTO.getTextPost(), postDTO.getStatusPost());
            postRepository.save(post);
            return ResponseEntity.ok("Пост успешно создан");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при создании поста");
        }
    }


    @GetMapping("/fetch/posts/all")
    public Iterable<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/fetch/posts/published")
    public Iterable<PostDTO> getPublishedPosts() {
        return postService.getPublishedPosts();
    }

    @PostMapping("/fetch/post/find")
    public ResponseEntity<String> savePost(@RequestBody Post post) {
        session.setAttribute("id", post.getIdPost());
        return ResponseEntity.ok("ID успешно сохранен в сессии");
    }

    @GetMapping("/fetch/post/load")
    public ResponseEntity<PostDTO> loadPost() {
        Long id = (Long) session.getAttribute("id");
        if (id != null) {
            PostDTO loadedReaction = new PostDTO();
            Post post = postRepository.findPostById(id);
            loadedReaction.setUsername(userRepository.findNick(post.getIdUser()));
            loadedReaction.setTextPost(post.getTextPost());
            loadedReaction.setStatusPost(post.getStatusPost());
            return ResponseEntity.ok(loadedReaction);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/fetch/post/delete")
    public void delPost(@RequestBody Post post) {
        postRepository.delPost(post.getIdPost());
    }

    @PostMapping("/fetch/post/edit")
    public ResponseEntity<String> editPost(@RequestBody PostDTO postDTO) {
        try {
            postRepository.editPost(postDTO.getIdPost(), userRepository.findIdUserByNick(postDTO.getUsername()), postDTO.getTextPost(), postDTO.getStatusPost());
            return ResponseEntity.ok("Пост успешно изменен");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при редактировании поста");
        }
    }
}

