package com.hragun.quotesblog.DTO;

public class UserAccDTO {
    private String nickUser;
    private int publicPosts;
    private int reactionPosts;
    private int offeredPosts;

    public String getNickUser() {
        return nickUser;
    }

    public void setNickUser(String nickUser) {
        this.nickUser = nickUser;
    }

    public int getReactionPosts() {
        return reactionPosts;
    }

    public void setReactionPosts(int reactionPosts) {
        this.reactionPosts = reactionPosts;
    }

    public int getPublicPosts() {
        return publicPosts;
    }

    public void setPublicPosts(int publicPosts) {
        this.publicPosts = publicPosts;
    }

    public int getOfferedPosts() {
        return offeredPosts;
    }

    public void setOfferedPosts(int offeredPosts) {
        this.offeredPosts = offeredPosts;
    }
}
