package com.revconnect.service;

import com.revconnect.model.Interaction;
import java.util.List;

public interface InteractionService {

    void likePost(int postId, int userId);

    void commentOnPost(Interaction interaction);

    List<Interaction> viewComments(int postId);
}
