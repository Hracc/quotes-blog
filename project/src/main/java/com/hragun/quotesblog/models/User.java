package com.hragun.quotesblog.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("users")
public class User {
    @Id
    @Column("idUser")
    private Long idUser;
    @Column("NickUser")
    private String username;
    @Column("PasswordUser")
    private String password;
    @Column("RoleUser")
    private String roleUser;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(String roleUser) {
        this.roleUser = roleUser;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public User(Long idUser, String username, String password, String roleUser) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.roleUser = roleUser;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(String username, String password,String roleUser) {
        this.username = username;
        this.password = password;
        this.roleUser = roleUser;
    }
    public User() {
    }
}
