package com.revconnect.service;

import com.revconnect.dao.*;

public class PostAnalyticsServiceImpl implements PostAnalyticsService {

    private IPostAnalyticsDao analyticsDao =
            new PostAnalyticsDaoImpl();

    @Override
    public void createAnalytics(int postId) {
        analyticsDao.createAnalytics(postId);
    }

    @Override
    public void updateAnalytics(int postId) {
        analyticsDao.updateAnalytics(postId);
    }

    @Override
    public void viewAnalytics(int postId) {
        analyticsDao.viewAnalytics(postId);
    }
}
