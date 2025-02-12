package edu.kirkwood.emeraldpark.model;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import static edu.kirkwood.shared.MySQL_Connect.getConnection;

public class TrailDAO {
    public static void main(String[] args) {
        getTrails().forEach(System.out::println);
    }

    public static List<Trail> getTrails() {
        List<Trail> trails = new ArrayList<>(); // makes array list
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_get_all_trails()}"); // call sp
            ResultSet rs = statement.executeQuery(); // execute query
            while (rs.next()) { // gets all the data
                int trail_id = rs.getInt("trail_id");
                String trail_name = rs.getString("trail_name");
                double trail_distance = rs.getDouble("trail_distance");
                TrailDifficulty trail_difficulty = TrailDifficulty.fromString(rs.getString("trail_difficulty"));
                LocalTime trail_time = rs.getTime("trail_time").toLocalTime();
                String trail_description = rs.getString("trail_description");
                Boolean allows_bikes = rs.getBoolean("allows_bikes");
                String trail_image = rs.getString("trail_image");
                trails.add(new Trail(trail_id, trail_name, trail_distance, trail_difficulty, trail_time, trail_description, allows_bikes, trail_image)); // matches the column names in the sp
                // instantiates the trail objects
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query error - " + e.getMessage());
        }
        return trails;
    }

    public static List<Trail> getTrailsAdmin() {
        List<Trail> trails = new ArrayList<>(); // makes array list
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_get_all_trails_admin()}"); // call sp
            ResultSet rs = statement.executeQuery(); // execute query
            while (rs.next()) {
                int trail_id = rs.getInt("trail_id");
                String trail_name = rs.getString("trail_name");
                double trail_distance = rs.getDouble("trail_distance");
                TrailDifficulty trail_difficulty = TrailDifficulty.fromString(rs.getString("trail_difficulty"));
                LocalTime trail_time = rs.getTime("trail_time").toLocalTime();
                String trail_description = rs.getString("trail_description");
                Boolean allows_bikes = rs.getBoolean("allows_bikes");
                String trail_image = rs.getString("trail_image");
                trails.add(new Trail(trail_id, trail_name, trail_distance, trail_difficulty, trail_time, trail_description, allows_bikes, trail_image)); // matches the column names in the sp
                // instantiates the trail objects
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query error - " + e.getMessage());
        }
        return trails;
    }

    public static boolean addTrail(Trail trail) {
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_add_trail_admin(?, ?, ?, ?, ?, ?, ?,?)}");
            statement.setInt(1, trail.getTrail_id());
            statement.setString(2, trail.getTrail_name());
            statement.setDouble(3, trail.getTrail_distance());

            // Used ChatGPT to convert TrailDifficulty to a database-compatible string
            if (trail.getTrail_difficulty() != null) {
                statement.setString(4, trail.getTrail_difficulty().toDatabaseString());
            } else {
                statement.setNull(4, java.sql.Types.VARCHAR);
            }

            // Used ChatGPT to convert LocalTime to SQL Time
            if (trail.getTrail_time() != null) {
                statement.setTime(5, java.sql.Time.valueOf(trail.getTrail_time()));
            } else {
                statement.setNull(5, java.sql.Types.TIME);
            }

            statement.setString(6, trail.getTrail_description());
            statement.setBoolean(7, trail.getAllows_bikes());
            statement.setString(8, trail.getTrail_image());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            return false;
        }
    }
}
