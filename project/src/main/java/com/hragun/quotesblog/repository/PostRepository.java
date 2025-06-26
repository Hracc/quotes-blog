package com.hragun.quotesblog.repository;

import com.hragun.quotesblog.models.Post;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    @Query("SELECT * FROM quotes_blog.posts p WHERE p.idUser=:idUser AND p.StatusPost='published'")
    List<Post> findPost(Long idUser);
    @Query("SELECT * FROM quotes_blog.posts ORDER BY idPost DESC")
    List<Post> findAllPosts();

    @Query("SELECT * FROM quotes_blog.posts p WHERE p.statusPost='published' ORDER BY p.idPost DESC")
    List<Post> findPublishedPosts();

    @Query("SELECT * FROM quotes_blog.posts p WHERE p.idPost=:idPost")
    Post findPostById(Long idPost);

    @Modifying
    @Query("DELETE FROM quotes_blog.posts p WHERE p.idPost = :idPost")
    int delPost(Long idPost);
    @Modifying
    @Query("UPDATE quotes_blog.posts AS p SET p.idUser=:idUser, p.TextPost=:TextPost, p.StatusPost=:StatusPost WHERE idPost=:idPost")
    int editPost(Long idPost, Long idUser, String TextPost, String StatusPost);
}
