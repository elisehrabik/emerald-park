package edu.kirkwood.emeraldpark.model;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        List<Review> reviews = new ArrayList<>(); // makes array list
        try(Connection connection = getConnection()){
            CallableStatement statement = connection.prepareCall("{CALL sp_get_all_reviews_admin()}"); // call sp
            ResultSet rs = statement.executeQuery(); // execute query
            while(rs.next()) { // gets all the data
                int review_id = rs.getInt("review_id");
                String trail_name = rs.getString("trail_name");
                String visitor_email = rs.getString("visitor_email");
                LocalDate review_date = rs.getDate("review_date").toLocalDate();
                int rating = rs.getInt("rating");
                LocalTime review_time = rs.getTime("review_time").toLocalTime();
                String review_notes = rs.getString("review_notes");

                reviews.add(new Review(review_id, trail_name, visitor_email, review_date, rating, review_time, review_notes));
            }
        } catch(SQLException e) {
            throw new RuntimeException("Query error - " + e.getMessage());
        }
        return reviews;
    }
}
