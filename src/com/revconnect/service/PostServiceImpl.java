package com.revconnect.service;

import com.revconnect.dao.*;
import com.revconnect.model.Post;

import java.util.List;

public class PostServiceImpl implements IPostService {

    private IPostDao postDao = new PostDaoImpl();

    @Override
    public void createPost(Post post) {
        int postId = postDao.createPost(post);
        if (postId > 0 && post.getHashtags() != null) {
            postDao.saveHashtags(postId, post.getHashtags());
        }
    }

    @Override
    public List<Post> viewAllPosts() {
        return postDao.getAllPosts();
    }
}
