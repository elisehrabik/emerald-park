package edu.kirkwood.emeraldpark.controller;

import edu.kirkwood.emeraldpark.model.Maintenance;
import edu.kirkwood.emeraldpark.model.MaintenanceDAO;
import edu.kirkwood.toystore.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(value="/maintenance")
public class AdminMaintenance extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User)session.getAttribute("activeUser");
        if(userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        List<Maintenance> maintenances = MaintenanceDAO.getMaintenanceAdmin();
        req.setAttribute("maintenances", maintenances);
        req.getRequestDispatcher("WEB-INF/admin-maintenance.jsp").forward(req, resp);
    }
}