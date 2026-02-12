package com.revconnect.dao;

import com.revconnect.model.Connection;
import com.revconnect.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionDaoImpl implements IConnectionDao {

    @Override
    public void sendRequest(int requesterId, int targetUserId) {

        String sql =
                "INSERT INTO connections " +
                        "(requester_id, target_user_id, status, created_at) " +
                        "VALUES (?, ?, 'PENDING', SYSDATE)";

        try (java.sql.Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, requesterId);
            ps.setInt(2, targetUserId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStatus(int connectionId, String status) {

        String sql =
                "UPDATE connections SET status=? WHERE connection_id=?";

        try (java.sql.Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, connectionId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Connection> getPendingRequests(int userId) {

        List<Connection> list = new ArrayList<>();

        String sql =
                "SELECT * FROM connections " +
                        "WHERE target_user_id=? AND status='PENDING'";

        try (java.sql.Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Connection c = new Connection();
                c.setConnectionId(rs.getInt("connection_id"));
                c.setRequesterId(rs.getInt("requester_id"));
                c.setTargetUserId(rs.getInt("target_user_id"));
                c.setStatus(rs.getString("status"));
                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Connection> getConnections(int userId) {

        List<Connection> list = new ArrayList<>();

        String sql =
                "SELECT * FROM connections " +
                        "WHERE (requester_id=? OR target_user_id=?) " +
                        "AND status='ACCEPTED'";

        try (java.sql.Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Connection c = new Connection();
                c.setConnectionId(rs.getInt("connection_id"));
                c.setRequesterId(rs.getInt("requester_id"));
                c.setTargetUserId(rs.getInt("target_user_id"));
                c.setStatus(rs.getString("status"));
                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}



