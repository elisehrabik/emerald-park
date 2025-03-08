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
    }
    public static List<Maintenance> getMaintenanceAdmin() {
        List<Maintenance> maintenances = new ArrayList<>(); // makes array list
        try(Connection connection = getConnection()){
            CallableStatement statement = connection.prepareCall("{CALL sp_get_all_maintenance_admin()}"); // call sp
            ResultSet rs = statement.executeQuery(); // execute query
            while(rs.next()) { // gets all the data
                int maintenance_id = rs.getInt("maintenance_id");
                String trail_name = rs.getString("trail_name");
                String first_name = rs.getString("first_name");
                String maintenance_type = rs.getString("maintenance_type");
                LocalDate request_date = rs.getDate("request_date").toLocalDate();
                java.sql.Date sqlCompletionDate = rs.getDate("completion_date");
                    LocalDate completion_date = (sqlCompletionDate != null) ? sqlCompletionDate.toLocalDate() : null; // Handle null
                boolean maintenance_complete  = rs.getBoolean("maintenance_complete");
                String maintenance_notes = rs.getString("maintenance_notes");
                maintenances.add(new Maintenance(maintenance_id, trail_name,first_name, maintenance_type, request_date, completion_date, maintenance_complete, maintenance_notes));
            }
        } catch(SQLException e) {
            throw new RuntimeException("Query error - " + e.getMessage());
        }
        return maintenances;
    }
}
