package edu.kirkwood.toystore.model;

import edu.kirkwood.shared.EmailThread;
import jakarta.servlet.http.HttpServletRequest;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static edu.kirkwood.shared.MySQL_Connect.getConnection;

public class UserDAO {
    public static void main(String[] args) {
//        getAll().forEach(System.out::println);
//        System.out.println(get("marc.hauschildt2@kirkwood.edu"));
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("P@ssw0rd".toCharArray());
        add(user);
    }

    public static boolean update(String originalEmail, User newUser) {
        User existingUser = get(originalEmail);
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_update_user(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")
        ) {
            statement.setInt(1, existingUser.getUserId());
            statement.setString(2, existingUser.getFirstName());
            statement.setString(3, existingUser.getLastName());
            statement.setString(4, existingUser.getEmail());
            statement.setString(5, existingUser.getPhone());
            statement.setString(6, existingUser.getLanguage());
            statement.setString(7, existingUser.getStatus());
            statement.setString(8, existingUser.getPrivileges());
            statement.setString(9, existingUser.getTimezone());
            statement.setString(10, existingUser.getPronouns());
            statement.setString(11, existingUser.getBirthday());
            statement.setString(12, existingUser.getAvatar());
            statement.setString(13, newUser.getFirstName());
            statement.setString(14, newUser.getLastName());
            statement.setString(15, newUser.getEmail());
            statement.setString(16, newUser.getPhone());
            statement.setString(17, newUser.getLanguage());
            statement.setString(18, newUser.getStatus());
            statement.setString(19, newUser.getPrivileges());
            statement.setString(20, newUser.getTimezone());
            statement.setString(21, newUser.getPronouns());
            statement.setString(22, newUser.getBirthday());
            statement.setString(23, newUser.getAvatar());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static String passwordReset(String email, HttpServletRequest req) {
        User user = get(email);
        if(user == null) {
            return "No user found that matches that email";
        } else {
            try(Connection connection = getConnection()) {
                String uuid = String.valueOf(UUID.randomUUID());
                CallableStatement statement = connection.prepareCall("{call sp_add_password_reset(?,?)}");
                statement.setString(1, email);
                statement.setString(2, uuid);
                int rowsAffected = statement.executeUpdate();
                if(rowsAffected > 0) {
                    // generate email html
                    String subject = "Reset Password";
                    String message = "<h2>Reset Password</h2>";
                    message += "<p>Please click this link to securely reset your password. This link expires in 30 minutes.</p>";
                    String appURL = "";
                    if(req.isSecure()) {
                        appURL = req.getServletContext().getInitParameter("appURLCloud");
                    } else {
                        appURL = req.getServletContext().getInitParameter("appURLLocal");
                    }
                    String fullURL = String.format("%s/new-password?token=%s", appURL, uuid);
                    message += String.format("<p><a href=\"%s\" target=\"_blank\">%s</a></p>", fullURL, fullURL);
                    message += "<p>If you did not request to reset your password, you can ignore this message and your password will not be changed.</p>";
                    // send email
                    EmailThread emailThread = new EmailThread(email, subject, message);
                    emailThread.start();
                    try {
                        emailThread.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    String errorMessage = emailThread.getErrorMessage();
                    if(errorMessage == null || errorMessage.isEmpty()) {
                        return "If there's an account associated with the email entered, we will send a password reset link.";
                    } else {
                        return errorMessage;
                    }
                } else {
                    return "Sorry, we couldn't process your password reset. Try again.";
                }
            } catch(SQLException e) {
                return "SQLException " + e.getMessage();
            }
        }
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
                String pronouns = rs.getString("pronouns");
                LocalDate birthday = rs.getDate("birthday").toLocalDate();
                String avatar = rs.getString("avatar");

                User user = new User(userId, firstName, lastName, email, phone, password, language, status, privileges, createdAt, timezone, pronouns, birthday, avatar);
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static User get(String email) {
        User user = null;
        try(Connection connection = getConnection()) {
            CallableStatement cstmt = connection.prepareCall("{call sp_get_user(?)}");


            cstmt.setString(1, email);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String phone = rs.getString("phone");
                char[] password = rs.getString("password").toCharArray();
                String language = rs.getString("language");
                String status = rs.getString("status");
                String privileges = rs.getString("privileges");
                Instant createdAt = rs.getTimestamp("created_at").toInstant();
                String timezone = rs.getString("timezone");

                String pronouns = rs.getString("pronouns");
                if (rs.wasNull()) pronouns = null;

                LocalDate birthday = rs.getDate("birthday") != null ? rs.getDate("birthday").toLocalDate() : null;

                String avatar = rs.getString("avatar");
                if (rs.wasNull()) avatar = null;

                user = new User(userId, firstName, lastName, email, phone, password, language, status, privileges, createdAt, timezone, pronouns, birthday, avatar);
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static boolean add(User user) {
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_add_user(?,?,?,?)}");
        ) {
            statement.setString(1, user.getEmail());
            statement.setString(2, BCrypt.hashpw(String.valueOf(user.getPassword()), BCrypt.gensalt(12)));
            statement.setString(3, user.getStatus());
            statement.setString(4, user.getPrivileges());
            int rowsAdded = statement.executeUpdate();
            return rowsAdded == 1;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPasswordReset(String token) {
        String email = "";
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall("{CALL sp_get_password_reset(?)}")) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Instant now = Instant.now();
                Instant created_at = resultSet.getTimestamp("created_at").toInstant();
                Duration duration = Duration.between(created_at, now);
                long minutesElapsed = duration.toMinutes();
                if(minutesElapsed < 30) {
                    email = resultSet.getString("email");
                }
                int id = resultSet.getInt("id");
                CallableStatement statement2 = connection.prepareCall("{CALL sp_delete_password_reset(?)}");
                statement2.setInt(1, id);
                statement2.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return email;
    }

    public static boolean updatePassword(String email, String password) {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                try (CallableStatement statement = connection.prepareCall("{CALL sp_update_user_password(?, ?)}")) {
                    statement.setString(1, email);
                    String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
                    statement.setString(2, encryptedPassword);
                    int rowsAffected = statement.executeUpdate();
                    return rowsAffected == 1;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean delete(User user) {
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_delete_user(?)}");
            statement.setInt(1, user.getUserId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}