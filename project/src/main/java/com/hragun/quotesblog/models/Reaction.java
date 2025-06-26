package com.hragun.quotesblog.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("reactions")
public class Reaction {
    @Id
    @Column("idReaction")
    private Long idReaction;
    @Column("nameReaction")
    private String nameReaction;

    public Reaction(){
    }
    public Reaction(Long idReaction, String nameReaction) {
        this.idReaction = idReaction;
        this.nameReaction = nameReaction;
    }

    public Long getIdReaction() {
        return idReaction;
    }

    public void setIdReaction(Long idReaction) {
        this.idReaction = idReaction;
    }

    public String getNameReaction() {
        return nameReaction;
    }

    public void setNameReaction(String nameReaction) {
        this.nameReaction = nameReaction;
    }
}
