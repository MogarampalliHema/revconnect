package com.revconnect.dao;

import com.revconnect.model.Message;
import com.revconnect.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDaoImpl implements IMessageDao {

    @Override
    public void sendMessage(Message message) {

        String sql =
                "INSERT INTO messages " +
                        "(sender_id, receiver_id, message_text, is_read, sent_at) " +
                        "VALUES (?, ?, ?, 0, SYSDATE)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, message.getSenderId());
            ps.setInt(2, message.getReceiverId());
            ps.setString(3, message.getMessageText());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Message> getChat(int user1, int user2) {

        List<Message> chat = new ArrayList<>();

        String sql =
                "SELECT sender_id, receiver_id, " +
                        "DBMS_LOB.SUBSTR(message_text, 500, 1) AS message_text, sent_at " +
                        "FROM messages " +
                        "WHERE (sender_id=? AND receiver_id=?) " +
                        "   OR (sender_id=? AND receiver_id=?) " +
                        "ORDER BY sent_at";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, user1);
            ps.setInt(2, user2);
            ps.setInt(3, user2);
            ps.setInt(4, user1);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Message m = new Message();
                m.setSenderId(rs.getInt("sender_id"));
                m.setReceiverId(rs.getInt("receiver_id"));
                m.setMessageText(rs.getString("message_text"));
                m.setSentAt(rs.getDate("sent_at"));
                chat.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return chat;
    }
}
