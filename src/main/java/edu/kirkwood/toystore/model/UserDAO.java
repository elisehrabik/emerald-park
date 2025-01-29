package edu.kirkwood.toystore.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static edu.kirkwood.shared.MySQL_Connect.getConnection;

public class UserDAO {
    public static void main(String[] args) {
        getAll().forEach(System.out::println);
    }
    public static List<User> getAll() {
        List<User> list = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_get_all_users()}");
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                char[] password = rs.getString("password").toCharArray();
                String language = rs.getString("language");
                String status = rs.getString("status");
                String privileges = rs.getString("privileges");
                Instant createdAt = rs.getTimestamp("created_at").toInstant();
                String timezone = rs.getString("timezone");
                User user = new User(userId, firstName, lastName, email, phone, password, language, status, privileges, createdAt, timezone);
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}