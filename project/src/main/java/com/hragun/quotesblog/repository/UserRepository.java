package com.hragun.quotesblog.repository;

import com.hragun.quotesblog.models.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT * FROM quotes_blog.users WHERE nickUser = :nickUser AND passwordUser = :passwordUser")
    User findNickAndPassUser(String nickUser, String passwordUser);

    @Query("SELECT * FROM quotes_blog.users WHERE NickUser =:NickUser")
    User findNickUser(String NickUser);

    @Query("SELECT * FROM quotes_blog.users")
    List<User> findAllUsers();
    @Query("SELECT u.idUser FROM quotes_blog.users u WHERE u.NickUser=:NickUser")
    Long findIdUserByNick(String NickUser);

    @Modifying
    @Query("DELETE FROM quotes_blog.users u WHERE u.idUser = :idUser")
    int delUser(Long idUser);


    @Query("SELECT * FROM quotes_blog.users u WHERE u.idUser=:idUser")
    User findUserById(Long idUser);

    @Modifying
    @Query("UPDATE quotes_blog.users AS u SET u.NickUser=:username, u.PasswordUser=:password, u.RoleUser=:roleUser WHERE u.idUser=:idUser")
    int editUser(Long idUser, String username, String password, String roleUser);

    //Для статистики профиля
    @Query("SELECT u.NickUser FROM quotes_blog.users u WHERE u.idUser = :idUser")
    String findNick(Long idUser);

    @Query("SELECT COUNT(DISTINCT CASE WHEN p.StatusPost = 'published' THEN p.idPost ELSE NULL END) AS 'publicPosts' FROM quotes_blog.users u LEFT JOIN quotes_blog.posts p ON u.idUser = p.idUser WHERE u.idUser=:idUser")
    int findPublishedPosts(Long idUser);

    @Query("SELECT COUNT(DISTINCT r.idReaction) AS 'reactionPosts' FROM quotes_blog.users u LEFT JOIN quotes_blog.posts_reactions_users r ON u.idUser = r.idUser WHERE u.idUser=:idUser")
    int findReactionPosts(Long idUser);

    @Query("SELECT COUNT(DISTINCT CASE WHEN p.StatusPost = 'offered' THEN p.idPost ELSE NULL END) AS 'offeredPosts' FROM quotes_blog.users u LEFT JOIN quotes_blog.posts p ON u.idUser = p.idUser WHERE u.idUser=:idUser")
    int findOfferedPosts(Long idUser);
}
