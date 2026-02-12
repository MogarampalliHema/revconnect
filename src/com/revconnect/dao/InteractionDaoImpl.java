package com.revconnect.dao;

import com.revconnect.model.Interaction;
import com.revconnect.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InteractionDaoImpl implements InteractionDao {

    @Override
    public void likePost(int postId, int userId) {

        String insertLike =
                "INSERT INTO interactions (post_id, user_id, interaction_type, created_at) " +
                        "VALUES (?, ?, 'LIKE', SYSDATE)";

        String updateAnalytics =
                "MERGE INTO post_analytics pa " +
                        "USING dual ON (pa.post_id = ?) " +
                        "WHEN MATCHED THEN " +
                        "UPDATE SET total_likes = NVL(pa.total_likes,0) + 1 " +
                        "WHEN NOT MATCHED THEN " +
                        "INSERT (post_id, total_likes, total_comments, total_shares, reach) " +
                        "VALUES (?, 1, 0, 0, 0)";

        try (Connection con = JDBCUtil.getConnection()) {

            PreparedStatement ps = con.prepareStatement(insertLike);
            ps.setInt(1, postId);
            ps.setInt(2, userId);
            ps.executeUpdate();

            PreparedStatement pa = con.prepareStatement(updateAnalytics);
            pa.setInt(1, postId);
            pa.setInt(2, postId);
            pa.executeUpdate();

            System.out.println("👍 Post liked");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commentOnPost(Interaction interaction) {

        String insertComment =
                "INSERT INTO interactions (post_id, user_id, interaction_type, comment_text, created_at) " +
                        "VALUES (?, ?, 'COMMENT', ?, SYSDATE)";

        String updateAnalytics =
                "MERGE INTO post_analytics pa " +
                        "USING dual ON (pa.post_id = ?) " +
                        "WHEN MATCHED THEN " +
                        "UPDATE SET total_comments = NVL(pa.total_comments,0) + 1 " +
                        "WHEN NOT MATCHED THEN " +
                        "INSERT (post_id, total_likes, total_comments, total_shares, reach) " +
                        "VALUES (?, 0, 1, 0, 0)";

        try (Connection con = JDBCUtil.getConnection()) {

            PreparedStatement ps = con.prepareStatement(insertComment);
            ps.setInt(1, interaction.getPostId());
            ps.setInt(2, interaction.getUserId());
            ps.setString(3, interaction.getCommentText());
            ps.executeUpdate();

            PreparedStatement pa = con.prepareStatement(updateAnalytics);
            pa.setInt(1, interaction.getPostId());
            pa.setInt(2, interaction.getPostId());
            pa.executeUpdate();

            System.out.println("💬 Comment added");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Interaction> getCommentsByPost(int postId) {

        List<Interaction> comments = new ArrayList<>();

        String sql =
                "SELECT user_id, DBMS_LOB.SUBSTR(comment_text, 500, 1) AS comment_text, created_at " +
                        "FROM interactions " +
                        "WHERE post_id=? AND interaction_type='COMMENT' " +
                        "ORDER BY created_at";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Interaction i = new Interaction();
                i.setUserId(rs.getInt("user_id"));
                i.setCommentText(rs.getString("comment_text"));
                i.setCreatedAt(rs.getDate("created_at"));
                comments.add(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }
}
