package com.revconnect.dao;

import com.revconnect.model.Post;
import java.util.List;

public interface IPostDao {

    int createPost(Post post);
    void saveHashtags(int postId, List<String> hashtags);
    List<Post> getAllPosts();
}
