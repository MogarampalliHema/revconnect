package com.revconnect.service;

import com.revconnect.dao.InteractionDao;
import com.revconnect.dao.InteractionDaoImpl;
import com.revconnect.model.Interaction;

import java.util.List;

public class InteractionServiceImpl implements InteractionService {

    private InteractionDao interactionDao =
            new InteractionDaoImpl();

    @Override
    public void likePost(int postId, int userId) {
        interactionDao.likePost(postId, userId);
    }

    @Override
    public void commentOnPost(Interaction interaction) {
        interactionDao.commentOnPost(interaction);
    }

    @Override
    public List<Interaction> viewComments(int postId) {
        return interactionDao.getCommentsByPost(postId);
    }
}
