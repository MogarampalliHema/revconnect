package com.revconnect.dao;

import com.revconnect.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PostAnalyticsDaoImpl implements IPostAnalyticsDao {

    // CREATE analytics row when post is created
    @Override
    public void createAnalytics(int postId) {

        String sql =
                "INSERT INTO post_analytics " +
                        "(post_id, total_likes, total_comments, total_shares, reach) " +
                        "VALUES (?, 0, 0, 0, 0)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, postId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE counts from interactions table
    @Override
    public void updateAnalytics(int postId) {

        String sql =
                "UPDATE post_analytics SET " +
                        "total_likes = (SELECT COUNT(*) FROM interactions " +
                        "               WHERE post_id=? AND interaction_type='LIKE'), " +
                        "total_comments = (SELECT COUNT(*) FROM interactions " +
                        "                  WHERE post_id=? AND interaction_type='COMMENT') " +
                        "WHERE post_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, postId);
            ps.setInt(2, postId);
            ps.setInt(3, postId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // VIEW analytics
    @Override
    public void viewAnalytics(int postId) {

        String sql =
                "SELECT total_likes, total_comments, total_shares, reach " +
                        "FROM post_analytics WHERE post_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- Post Analytics ---");
                System.out.println("Likes: " + rs.getInt("total_likes"));
                System.out.println("Comments: " + rs.getInt("total_comments"));
                System.out.println("Shares: " + rs.getInt("total_shares"));
                System.out.println("Reach: " + rs.getInt("reach"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
