package com.revconnect.dao;

import com.revconnect.model.Notification;
import com.revconnect.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDaoImpl implements INotificationDao {

    @Override
    public void createNotification(
            int userId,
            String type,
            int referenceId,
            String message) {

        String sql =
                "INSERT INTO notifications " +
                        "(user_id, notification_type, reference_id, message, is_read, created_at) " +
                        "VALUES (?, ?, ?, ?, 0, SYSDATE)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, type);
            ps.setInt(3, referenceId);
            ps.setString(4, message);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Notification> getNotifications(int userId) {

        List<Notification> list = new ArrayList<>();

        String sql =
                "SELECT notification_id, notification_type, " +
                        "DBMS_LOB.SUBSTR(message, 500, 1) AS message, " +
                        "is_read, created_at " +
                        "FROM notifications " +
                        "WHERE user_id=? ORDER BY created_at DESC";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Notification n = new Notification();
                n.setNotificationId(rs.getInt("notification_id"));
                n.setNotificationType(rs.getString("notification_type"));
                n.setMessage(rs.getString("message"));
                n.setIsRead(rs.getInt("is_read"));
                n.setCreatedAt(rs.getDate("created_at"));
                list.add(n);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void markAsRead(int notificationId) {

        String sql =
                "UPDATE notifications SET is_read=1 WHERE notification_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, notificationId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
