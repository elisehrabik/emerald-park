package edu.kirkwood.emeraldpark.model;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static edu.kirkwood.shared.MySQL_Connect.getConnection;

public class ReviewDAO {
    public static void main(String[] args) {
        getReviewsAdmin().forEach(System.out::println);
    }
    public static List<Review> getReviewsAdmin() {
        List<Review> reviews = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_get_all_reviews_admin()}");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int review_id = rs.getInt("review_id");
                int trail_id = rs.getInt("trail_id");
                String trail_name = rs.getString("trail_name");
                int user_id = rs.getInt("user_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String avatar = rs.getString("avatar");
                String pronouns = rs.getString("pronouns");
                Instant created_at = rs.getTimestamp("created_at").toInstant();
                LocalDate review_date = rs.getDate("review_date").toLocalDate();
                int rating = rs.getInt("rating");
                String review_notes = rs.getString("review_notes");

                Review review = new Review(review_id, trail_id, trail_name, user_id, first_name, last_name,
                        avatar, pronouns, created_at,
                        review_date, rating, review_notes);
                reviews.add(review);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query error - " + e.getMessage());
        }
        return reviews;
    }

    public static Review getReviewById(int review_id) {
        Review review = null;
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_get_review_by_id(?)}");
            statement.setInt(1, review_id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int trail_id = rs.getInt("trail_id");
                String trail_name = rs.getString("trail_name");
                int user_id = rs.getInt("user_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String avatar = rs.getString("avatar");
                String pronouns = rs.getString("pronouns");
                Instant created_at = rs.getTimestamp("created_at").toInstant();
                LocalDate review_date = rs.getDate("review_date").toLocalDate();
                int rating = rs.getInt("rating");
                String review_notes = rs.getString("review_notes");

                review = new Review(
                        review_id, trail_id, trail_name, user_id, first_name, last_name,
                        avatar, pronouns, created_at,
                        review_date, rating, review_notes
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query error - " + e.getMessage());
        }
        return review;
    }

    public static boolean deleteReview(int review_id) {
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall("{CALL sp_delete_review(?)}")) {
            callableStatement.setInt(1, review_id);
            return callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Review> getReviewsByTrailId(int trail_id) {
        List<Review> reviews = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_get_reviews_by_trail_id(?)}");
            statement.setInt(1, trail_id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int review_id = rs.getInt("review_id");
                String trail_name = rs.getString("trail_name");
                int user_id = rs.getInt("user_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String avatar = rs.getString("avatar");
                String pronouns = rs.getString("pronouns");
                Instant created_at = rs.getTimestamp("created_at").toInstant();
                LocalDate review_date = rs.getDate("review_date").toLocalDate();
                int rating = rs.getInt("rating");
                String review_notes = rs.getString("review_notes");

                Review review = new Review(
                        review_id, trail_id, trail_name, user_id, first_name, last_name,
                        avatar, pronouns, created_at,
                        review_date, rating, review_notes
                );

                reviews.add(review);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query error - " + e.getMessage());
        }
        return reviews;
    }


}
