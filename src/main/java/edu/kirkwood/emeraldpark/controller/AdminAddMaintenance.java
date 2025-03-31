package edu.kirkwood.emeraldpark.controller;

import edu.kirkwood.emeraldpark.model.*;
import edu.kirkwood.toystore.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@WebServlet("/admin-add-maintenance")
public class AdminAddMaintenance extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");
        if (userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        List<Maintenance> maintenances = MaintenanceDAO.getMaintenanceAdmin();
        req.setAttribute("maintenances", maintenances);

        List<Trail> trails = TrailDAO.getTrails(100, 0, "");
        req.setAttribute("trails", trails);

        req.setAttribute("pageTitle", "Add Maintenance Request");

        req.getRequestDispatcher("WEB-INF/admin-add-maintenance.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");
        if (userFromSession == null) {
            resp.sendRedirect("login");
            return;
        }

        String trailIdParam = req.getParameter("trailId");
        String maintenanceType = req.getParameter("maintenanceType");
        String requestDateParam = req.getParameter("requestDate");
        String completionDateParam = req.getParameter("completionDate");
        String maintenanceCompleteParam = req.getParameter("maintenanceComplete");
        String maintenanceNotes = req.getParameter("maintenanceNotes");

        boolean validationError = false;
        Maintenance maintenance = new Maintenance();

        int trailId = -1;
        if (trailIdParam == null || trailIdParam.trim().isEmpty()) {
            validationError = true;
            req.setAttribute("trailIdError", true);
            req.setAttribute("trailIdMessage", "Trail selection is required.");
        } else {
            try {
                trailId = Integer.parseInt(trailIdParam);
                maintenance.setTrail_id(trailId);
                req.setAttribute("trailIdError", false);
                req.setAttribute("trailIdMessage", "Looks good!");
            } catch (NumberFormatException e) {
                validationError = true;
                req.setAttribute("trailIdError", true);
                req.setAttribute("trailIdMessage", "Invalid trail selection.");
            }
        }
        req.setAttribute("trailId", trailIdParam);

        int userId = userFromSession.getUserId();
        maintenance.setUser_id(userId);

        if (maintenanceType == null || maintenanceType.trim().isEmpty()) {
            validationError = true;
            req.setAttribute("maintenanceTypeError", true);
            req.setAttribute("maintenanceTypeMessage", "Maintenance type cannot be empty.");
        } else {
            maintenance.setMaintenance_type(maintenanceType);
            req.setAttribute("maintenanceTypeError", false);
            req.setAttribute("maintenanceTypeMessage", "Looks good!");
        }
        req.setAttribute("maintenanceType", maintenanceType);

        LocalDate requestDate = LocalDate.now();
        if (requestDateParam != null && !requestDateParam.trim().isEmpty()) {
            try {
                requestDate = LocalDate.parse(requestDateParam);
            } catch (Exception e) {
                validationError = true;
                req.setAttribute("requestDateError", true);
                req.setAttribute("requestDateMessage", "Invalid Request Date. Use YYYY-MM-DD format.");
            }
        }
        maintenance.setRequest_date(requestDate);

        Date requestDateAsDate = Date.from(requestDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        req.setAttribute("requestDate", requestDateAsDate);
        req.setAttribute("requestDateError", false);
        req.setAttribute("requestDateMessage", "Looks good!");

        LocalDate completionDate = null;
        Date completionDateAsDate = null; // Initialize a Date object

        if (completionDateParam != null && !completionDateParam.trim().isEmpty()) {
            try {
                completionDate = LocalDate.parse(completionDateParam);
                completionDateAsDate = Date.from(completionDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                req.setAttribute("completionDateError", false);
                req.setAttribute("completionDateMessage", "Looks good!");
            } catch (Exception e) {
                validationError = true;
                req.setAttribute("completionDateError", true);
                req.setAttribute("completionDateMessage", "Invalid Completion Date. Use YYYY-MM-DD format.");
            }
        }

        maintenance.setCompletion_date(completionDate);
        req.setAttribute("completionDate", completionDateAsDate);

        boolean maintenanceComplete = "1".equals(maintenanceCompleteParam);
        maintenance.setMaintenance_complete(maintenanceComplete);
        req.setAttribute("maintenanceComplete", maintenanceCompleteParam);
        req.setAttribute("maintenanceCompleteError", false);
        req.setAttribute("maintenanceCompleteMessage", "Looks good!");

        if (maintenanceNotes == null || maintenanceNotes.trim().isEmpty()) {
            validationError = true;
            req.setAttribute("maintenanceNotesError", true);
            req.setAttribute("maintenanceNotesMessage", "Maintenance notes cannot be empty.");
        } else {
            maintenance.setMaintenance_notes(maintenanceNotes);
            req.setAttribute("maintenanceNotesError", false);
            req.setAttribute("maintenanceNotesMessage", "Looks good!");
        }
        req.setAttribute("maintenanceNotes", maintenanceNotes);

        if (!validationError) {
            boolean maintenanceAdded = MaintenanceDAO.addMaintenance(maintenance);
            req.setAttribute("maintenanceAdded", maintenanceAdded);
            req.setAttribute("maintenanceAddedMessage", maintenanceAdded ? "Successfully added maintenance request!" : "Error adding maintenance request.");
        }

        List<Trail> trails = TrailDAO.getTrails(100, 0, "");
        req.setAttribute("trails", trails);
        req.setAttribute("pageTitle", "Add Maintenance Request");


        req.getRequestDispatcher("WEB-INF/admin-add-maintenance.jsp").forward(req, resp);
    }
}
