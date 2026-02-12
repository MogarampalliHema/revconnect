package com.revconnect.service;

import com.revconnect.model.Post;
import java.util.List;

public interface IPostService {

    void createPost(Post post);
    List<Post> viewAllPosts();
}
