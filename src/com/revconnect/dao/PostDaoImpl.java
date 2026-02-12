package com.revconnect.dao;

import com.revconnect.model.Post;
import com.revconnect.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDaoImpl implements IPostDao {

    // ---------------- CREATE POST ----------------
    @Override
    public int createPost(Post post) {

        String sql =
                "INSERT INTO posts (user_id, content, created_at) VALUES (?, ?, SYSDATE)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql, new String[]{"post_id"})) {

            ps.setInt(1, post.getUserId());
            ps.setString(2, post.getContent());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // ---------------- SAVE HASHTAGS ----------------
    @Override
    public void saveHashtags(int postId, List<String> hashtags) {

        String selectHashtag =
                "SELECT hashtag_id FROM hashtags WHERE tag_name = ?";

        String insertHashtag =
                "INSERT INTO hashtags (tag_name) VALUES (?)";

        String insertMapping =
                "INSERT INTO post_hashtags (post_id, hashtag_id) VALUES (?, ?)";

        try (Connection con = JDBCUtil.getConnection()) {

            for (String tag : hashtags) {

                tag = tag.trim().toLowerCase();
                int hashtagId;

                // check if hashtag exists
                PreparedStatement psSelect = con.prepareStatement(selectHashtag);
                psSelect.setString(1, tag);
                ResultSet rs = psSelect.executeQuery();

                if (rs.next()) {
                    hashtagId = rs.getInt("hashtag_id");
                } else {
                    PreparedStatement psInsert =
                            con.prepareStatement(insertHashtag, new String[]{"hashtag_id"});
                    psInsert.setString(1, tag);
                    psInsert.executeUpdate();

                    ResultSet rs2 = psInsert.getGeneratedKeys();
                    rs2.next();
                    hashtagId = rs2.getInt(1);
                }

                // map post ↔ hashtag
                PreparedStatement psMap = con.prepareStatement(insertMapping);
                psMap.setInt(1, postId);
                psMap.setInt(2, hashtagId);
                psMap.executeUpdate();
            }

            System.out.println("✅ Hashtags saved successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- VIEW ALL POSTS (FIXED FOR CLOB) ----------------
    @Override
    public List<Post> getAllPosts() {

        List<Post> posts = new ArrayList<>();

        String sql =
                "SELECT p.post_id, " +
                        "DBMS_LOB.SUBSTR(p.content, 1000, 1) AS content, " +
                        "p.created_at, " +
                        "LISTAGG(h.tag_name, ', ') " +
                        "   WITHIN GROUP (ORDER BY h.tag_name) AS tags " +
                        "FROM posts p " +
                        "LEFT JOIN post_hashtags ph ON p.post_id = ph.post_id " +
                        "LEFT JOIN hashtags h ON ph.hashtag_id = h.hashtag_id " +
                        "GROUP BY p.post_id, p.created_at, DBMS_LOB.SUBSTR(p.content, 1000, 1) " +
                        "ORDER BY p.created_at DESC";

        try (Connection con = JDBCUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("post_id"));
                post.setContent(rs.getString("content"));
                post.setCreatedAt(rs.getDate("created_at"));

                System.out.println("\nPost ID: " + post.getPostId());
                System.out.println("Content: " + post.getContent());
                System.out.println("Hashtags: " + rs.getString("tags"));
                System.out.println("Date: " + post.getCreatedAt());

                posts.add(post);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }
}
