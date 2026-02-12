package com.revconnect.dao;

import com.revconnect.model.User;
import com.revconnect.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements IUserDao {

    @Override
    public void register(User user) {

        String sql =
                "INSERT INTO users (email, username, password, role) VALUES (?,?,?,?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());

            ps.executeUpdate();
            System.out.println("✅ User Registered Successfully");

        } catch (Exception e) {
            System.out.println("❌ Registration Failed");
            e.printStackTrace();
        }
    }

    @Override
    public User login(String username, String password) {

        String sql =
                "SELECT user_id, username, role FROM users WHERE username=? AND password=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
