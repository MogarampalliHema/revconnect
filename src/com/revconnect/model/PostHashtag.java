package com.revconnect.model;

public class PostHashtag {

    private int postId;
    private int hashtagId;

    public PostHashtag() {}

    public PostHashtag(int postId, int hashtagId) {
        this.postId = postId;
        this.hashtagId = hashtagId;
    }

    public int getPostId() { return postId; }
    public void setPostId(int postId) { this.postId = postId; }

    public int getHashtagId() { return hashtagId; }
    public void setHashtagId(int hashtagId) { this.hashtagId = hashtagId; }
}
