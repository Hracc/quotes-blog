package com.hragun.quotesblog.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("posts")
public class Post {
    @Id
    @Column("idPost")
    public Long idPost;
    @Column("idUser")
    private Long idUser;
    @Column("TextPost")
    private String textPost;

    @Column("StatusPost")
    private String statusPost;

    public Post() {
    }

    public Post(Long idPost, Long idUser, String textPost, String statusPost) {
        this.idPost = idPost;
        this.idUser = idUser;
        this.textPost = textPost;
        this.statusPost = statusPost;
    }
    public Post(Long idUser, String textPost, String statusPost) {
        this.idUser = idUser;
        this.textPost = textPost;
        this.statusPost= statusPost;
    }

    public String getStatusPost() {
        return statusPost;
    }

    public void setStatusPost(String statusPost) {
        this.statusPost = statusPost;
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getTextPost() {
        return textPost;
    }

    public void setTextPost(String textPost) {
        this.textPost = textPost;
    }

}

