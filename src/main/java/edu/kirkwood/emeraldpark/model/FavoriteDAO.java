package edu.kirkwood.emeraldpark.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static edu.kirkwood.shared.MySQL_Connect.getConnection;

public class FavoriteDAO {

    public static boolean addFavorite(int userId, int trailId) {
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_add_favorite(?, ?)}");
            statement.setInt(1, userId);
            statement.setInt(2, trailId);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Error adding favorite: " + e.getMessage());
            return false;
        }
    }

    public static boolean removeFavorite(int userId, int trailId) {
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_remove_favorite(?, ?)}");
            statement.setInt(1, userId);
            statement.setInt(2, trailId);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Error removing favorite: " + e.getMessage());
            return false;
        }
    }

    public static boolean isFavorite(int userId, int trailId) {
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_is_favorite(?, ?)}");
            statement.setInt(1, userId);
            statement.setInt(2, trailId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("is_favorite") > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error checking favorite: " + e.getMessage());
        }
        return false;
    }

    public static List<Integer> getFavoritesByUser(int userId) {
        List<Integer> trailIds = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_get_favorites_by_user(?)}");
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                trailIds.add(rs.getInt("trail_id"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching user favorites: " + e.getMessage());
        }
        return trailIds;
    }
}
