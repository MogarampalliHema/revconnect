package com.revconnect.dao;

import com.revconnect.model.Profile;
import com.revconnect.util.JDBCUtil;

import java.sql.*;

public class ProfileDaoImpl implements IProfileDao {

    @Override
    public void createProfile(Profile profile) {

        String sql =
                "INSERT INTO profiles " +
                        "(user_id, display_name, bio, address, contact_info, website, profile_picture_path) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, profile.getUserId());
            ps.setString(2, profile.getDisplayName());
            ps.setString(3, profile.getBio());
            ps.setString(4, profile.getAddress());
            ps.setString(5, profile.getContactInfo());
            ps.setString(6, profile.getWebsite());
            ps.setString(7, profile.getProfilePicturePath());

            ps.executeUpdate();
            System.out.println("✅ Profile created successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Profile getProfileByUserId(int userId) {

        String sql =
                "SELECT profile_id, display_name, " +
                        "DBMS_LOB.SUBSTR(bio, 500, 1) AS bio, " +
                        "address, contact_info, website, profile_picture_path " +
                        "FROM profiles WHERE user_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Profile p = new Profile();
                p.setProfileId(rs.getInt("profile_id"));
                p.setUserId(userId);
                p.setDisplayName(rs.getString("display_name"));
                p.setBio(rs.getString("bio"));
                p.setAddress(rs.getString("address"));
                p.setContactInfo(rs.getString("contact_info"));
                p.setWebsite(rs.getString("website"));
                p.setProfilePicturePath(rs.getString("profile_picture_path"));
                return p;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateProfile(Profile profile) {

        String sql =
                "UPDATE profiles SET " +
                        "display_name=?, bio=?, address=?, contact_info=?, website=?, profile_picture_path=? " +
                        "WHERE user_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, profile.getDisplayName());
            ps.setString(2, profile.getBio());
            ps.setString(3, profile.getAddress());
            ps.setString(4, profile.getContactInfo());
            ps.setString(5, profile.getWebsite());
            ps.setString(6, profile.getProfilePicturePath());
            ps.setInt(7, profile.getUserId());

            ps.executeUpdate();
            System.out.println("✅ Profile updated successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
