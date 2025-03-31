package edu.kirkwood.emeraldpark.model;


import edu.kirkwood.emeraldpark.model.TrailCategory;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import static edu.kirkwood.shared.MySQL_Connect.getConnection;

public class TrailDAO {
    public static void main(String[] args) {
        getAllCategories().forEach(System.out::println);
    }public static List<Trail> getTrails(int limit, int offset, String categories, String[] difficulties) {
        List<Trail> trails = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_get_all_trails_categorized(?,?,?,?)}");

            statement.setInt(1, limit);
            statement.setInt(2, offset);
            statement.setString(3, categories != null ? categories : "");

            String difficultiesStr = (difficulties != null && difficulties.length > 0) ? String.join(",", difficulties) : "";
            statement.setString(4, difficultiesStr);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int trail_id = rs.getInt("trail_id");
                String trail_name = rs.getString("trail_name");
                double trail_distance = rs.getDouble("trail_distance");
                TrailDifficulty trail_difficulty = TrailDifficulty.fromString(rs.getString("trail_difficulty"));
                LocalTime trail_time = rs.getTime("trail_time").toLocalTime();
                String trail_description = rs.getString("trail_description");
                String trail_image = rs.getString("trail_image");
                int categoryId = rs.getInt("category_id");
                String categoryName = rs.getString("category_name");

                trails.add(new Trail(trail_id, trail_name, trail_distance, trail_difficulty, trail_time, trail_description, trail_image, categoryId, categoryName));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query error - " + e.getMessage());
        }
        return trails;
    }

    public static List<Trail> getTrailsAdmin(int limit, int offset, String categoryId) {
        List<Trail> trails = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_get_all_trails_admin(?, ?, ?)}");
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            statement.setString(3, categoryId);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int trail_id = rs.getInt("trail_id");
                String trail_name = rs.getString("trail_name");
                double trail_distance = rs.getDouble("trail_distance");
                TrailDifficulty trail_difficulty = TrailDifficulty.fromString(rs.getString("trail_difficulty"));
                LocalTime trail_time = rs.getTime("trail_time").toLocalTime();
                String trail_description = rs.getString("trail_description");
                String trail_image = rs.getString("trail_image");
                int categoryIdInt = rs.getInt("category_id");
                String categoryName = rs.getString("category_name");

                trails.add(new Trail(trail_id, trail_name, trail_distance, trail_difficulty, trail_time, trail_description, trail_image, categoryIdInt, categoryName));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query error - " + e.getMessage());
        }
        return trails;
    }


    public static boolean addTrail(Trail trail) {
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_add_trail_admin(?, ?, ?, ?, ?, ?, ?, ?)}");
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
            statement.setString(7, trail.getTrail_image());
            statement.setInt(8, trail.getCategoryId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<TrailCategory> getAllCategories() {
        List<TrailCategory> categories = new ArrayList<>();
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall("{CALL sp_get_trail_categories()}");
             ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int numProducts = resultSet.getInt("num_products");
                categories.add(new TrailCategory(id, name, numProducts));
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }

    public static boolean updateTrail(Trail trailOriginal, Trail trailNew) {
        try (Connection connection = getConnection()) {
            if (connection == null) {
                System.err.println("Database connection is null.");
                return false;
            }

            CallableStatement statement = connection.prepareCall("{CALL sp_update_trail(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            statement.setInt(1, trailOriginal.getTrail_id());
            statement.setString(2, trailOriginal.getTrail_name());
            statement.setDouble(3, trailOriginal.getTrail_distance());

            if (trailOriginal.getTrail_difficulty() != null) {
                statement.setString(4, trailOriginal.getTrail_difficulty().toDatabaseString());
            } else {
                statement.setNull(4, java.sql.Types.VARCHAR);
            }

            if (trailOriginal.getTrail_time() != null) {
                statement.setTime(5, java.sql.Time.valueOf(trailOriginal.getTrail_time()));
            } else {
                statement.setNull(5, java.sql.Types.TIME);
            }

            statement.setString(6, trailOriginal.getTrail_description());
            statement.setString(7, trailOriginal.getTrail_image());
            statement.setInt(8, trailOriginal.getCategoryId());

            statement.setInt(9, trailNew.getTrail_id());
            statement.setString(10, trailNew.getTrail_name());
            statement.setDouble(11, trailNew.getTrail_distance());

            if (trailNew.getTrail_difficulty() != null) {
                statement.setString(12, trailNew.getTrail_difficulty().toDatabaseString());
            } else {
                statement.setNull(12, java.sql.Types.VARCHAR);
            }

            if (trailNew.getTrail_time() != null) {
                statement.setTime(13, java.sql.Time.valueOf(trailNew.getTrail_time()));
            } else {
                statement.setNull(13, java.sql.Types.TIME);
            }

            statement.setString(14, trailNew.getTrail_description());
            statement.setString(15, trailNew.getTrail_image());
            statement.setInt(16, trailNew.getCategoryId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQL Error: " + e.getMessage());
            return false;
        }
    }



    public static Trail getTrail(String trail_id) {
        Trail trail = null;
        if (trail_id != null) {
            trail_id = trail_id.trim();
            try {
                int trailIdInt = Integer.parseInt(trail_id);

                try (Connection connection = getConnection()) {
                    CallableStatement statement = connection.prepareCall("{CALL sp_get_trail_by_id(?)}");
                    statement.setInt(1, trailIdInt);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        String trail_name = resultSet.getString("trail_name");
                        double trail_distance = resultSet.getDouble("trail_distance");
                        TrailDifficulty trail_difficulty = TrailDifficulty.fromString(resultSet.getString("trail_difficulty"));
                        LocalTime trail_time = resultSet.getTime("trail_time").toLocalTime();
                        String trail_description = resultSet.getString("trail_description");
                        String trail_image = resultSet.getString("trail_image");
                        int categoryId = resultSet.getInt("category_id");
                        String categoryName = resultSet.getString("category_name");

                        trail = new Trail(trailIdInt, trail_name, trail_distance, trail_difficulty, trail_time, trail_description, trail_image, categoryId, categoryName);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid trail_id format: must be an integer.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return trail;
    }


    public static boolean deleteTrail(int trailId) {
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall("{CALL sp_delete_trail(?)}")) {
             callableStatement.setInt(1, trailId);
             return callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




}