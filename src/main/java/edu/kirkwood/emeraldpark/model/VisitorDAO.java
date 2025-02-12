package edu.kirkwood.emeraldpark.model;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static edu.kirkwood.shared.MySQL_Connect.getConnection;

public class VisitorDAO {
    public static void main(String[] args) {
        getVisitorsAdmin().forEach(System.out::println);
    }
    public static List<Visitor> getVisitorsAdmin() {
        List<Visitor> visitors = new ArrayList<>(); // makes array list
        try(Connection connection = getConnection()){
            CallableStatement statement = connection.prepareCall("{CALL sp_get_all_visitors_admin()}"); // call sp
            ResultSet rs = statement.executeQuery(); // execute query
            while(rs.next()) { // gets all the data
                int visitor_id = rs.getInt("visitor_id");
                String visitor_first_name = rs.getString("visitor_first_name");
                String visitor_last_name = rs.getString("visitor_last_name");
                String visitor_email = rs.getString("visitor_email");

                visitors.add(new Visitor(visitor_id, visitor_first_name, visitor_last_name, visitor_email));
            }
        } catch(SQLException e) {
            throw new RuntimeException("Query error - " + e.getMessage());
        }
        return visitors;
    }
}
