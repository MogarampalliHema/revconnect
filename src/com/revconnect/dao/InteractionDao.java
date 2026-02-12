package com.revconnect.dao;

import com.revconnect.model.Interaction;
import java.util.List;

public interface InteractionDao {

    void likePost(int postId, int userId);
    void commentOnPost(Interaction interaction);
    List<Interaction> getCommentsByPost(int postId);
}
