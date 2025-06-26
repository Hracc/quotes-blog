package com.hragun.quotesblog.repository;

import com.hragun.quotesblog.models.Reaction;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends CrudRepository<Reaction, Long> {
    @Query("SELECT * FROM quotes_blog.reactions r WHERE r.idReaction=:idReaction")
    Reaction findReactionById(Long idReaction);
    @Modifying
    @Query("DELETE FROM quotes_blog.reactions r WHERE r.idReaction = :idReaction")
    int delReactn(Long idReaction);
    @Modifying
    @Query("UPDATE quotes_blog.reactions AS r SET r.nameReaction=:nameReaction WHERE r.idReaction =:idReaction")
    int editReactn(Long idReaction, String nameReaction);
}
