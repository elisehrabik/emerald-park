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

public class MaintenanceDAO {
    public static void main(String[] args) {
        getMaintenanceAdmin().forEach(System.out::println);
    }public static List<Maintenance> getMaintenanceAdmin() {
        List<Maintenance> maintenances = new ArrayList<>();
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_get_all_maintenance_admin()}");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int maintenance_id = rs.getInt("maintenance_id");
                int trail_id = rs.getInt("trail_id");
                String trail_name = rs.getString("trail_name");
                int user_id = rs.getInt("user_id");
                String first_name = rs.getString("first_name");
                String maintenance_type = rs.getString("maintenance_type");
                LocalDate request_date = rs.getDate("request_date").toLocalDate();
                java.sql.Date sqlCompletionDate = rs.getDate("completion_date");
                LocalDate completion_date = (sqlCompletionDate != null) ? sqlCompletionDate.toLocalDate() : null;
                boolean maintenance_complete = rs.getBoolean("maintenance_complete");
                String maintenance_notes = rs.getString("maintenance_notes");
                Maintenance maintenance = new Maintenance(
                        maintenance_id, trail_id, trail_name, user_id, first_name,
                        maintenance_type, request_date, completion_date, maintenance_complete, maintenance_notes
                );
                maintenances.add(maintenance);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query error - " + e.getMessage());
        }
        return maintenances;
    }


    public static boolean addMaintenance(Maintenance maintenance) {
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_add_maintenance(?, ?, ?, ?, ?, ?, ?)}");

            statement.setInt(1, maintenance.getTrail_id());
            statement.setInt(2, maintenance.getUser_id());
            statement.setString(3, maintenance.getMaintenance_type());


            if (maintenance.getRequest_date() != null) {
                statement.setDate(4, java.sql.Date.valueOf(maintenance.getRequest_date()));
            } else {
                statement.setNull(4, java.sql.Types.DATE);
            }

            if (maintenance.getCompletion_date() != null) {
                statement.setDate(5, java.sql.Date.valueOf(maintenance.getCompletion_date()));
            } else {
                statement.setNull(5, java.sql.Types.DATE);
            }

            statement.setBoolean(6, maintenance.isMaintenance_complete());
            statement.setString(7, maintenance.getMaintenance_notes());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static Maintenance getMaintenanceById(int maintenance_id) {
        Maintenance maintenance = null;
        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{CALL sp_get_maintenance_by_id(?)}");
            statement.setInt(1, maintenance_id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int trail_id = rs.getInt("trail_id");
                String trail_name = rs.getString("trail_name");
                int user_id = rs.getInt("user_id");
                String first_name = rs.getString("first_name");
                String maintenance_type = rs.getString("maintenance_type");
                LocalDate request_date = rs.getDate("request_date").toLocalDate();
                java.sql.Date sqlCompletionDate = rs.getDate("completion_date");
                LocalDate completion_date = (sqlCompletionDate != null) ? sqlCompletionDate.toLocalDate() : null;
                boolean maintenance_complete = rs.getBoolean("maintenance_complete");
                String maintenance_notes = rs.getString("maintenance_notes");

                maintenance = new Maintenance(
                        maintenance_id, trail_id, trail_name, user_id, first_name,
                        maintenance_type, request_date, completion_date, maintenance_complete, maintenance_notes
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query error - " + e.getMessage());
        }
        return maintenance;
    }


    public static boolean updateMaintenance(Maintenance maintenanceOriginal, Maintenance maintenanceNew) {
        try (Connection connection = getConnection()) {
            if (connection == null) {
                System.err.println("Database connection is null.");
                return false;
            }

            CallableStatement statement = connection.prepareCall("{CALL sp_update_maintenance(?, ?, ?, ?, ?, ?, ?)}");

            statement.setInt(1, maintenanceOriginal.getMaintenance_id());

            if (maintenanceOriginal.getCompletion_date() != null) {
                statement.setDate(2, java.sql.Date.valueOf(maintenanceOriginal.getCompletion_date()));
            } else {
                statement.setNull(2, java.sql.Types.DATE);
            }

            statement.setBoolean(3, maintenanceOriginal.isMaintenance_complete());
            statement.setString(4, maintenanceOriginal.getMaintenance_notes());

            if (maintenanceNew.getCompletion_date() != null) {
                statement.setDate(5, java.sql.Date.valueOf(maintenanceNew.getCompletion_date()));
            } else {
                statement.setNull(5, java.sql.Types.DATE);
            }

            statement.setBoolean(6, maintenanceNew.isMaintenance_complete());
            statement.setString(7, maintenanceNew.getMaintenance_notes());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQL Error: " + e.getMessage());
            return false;
        }
    }

}
