package edu.kirkwood.emeraldpark.controller;

import edu.kirkwood.toystore.model.User;
import edu.kirkwood.toystore.model.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = "/admin-update-user")
public class AdminUpdateUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");

        if (userFromSession == null || !"active".equals(userFromSession.getStatus()) || !"admin".equals(userFromSession.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String userIdStr = req.getParameter("user_id");
        if (userIdStr == null || userIdStr.trim().isEmpty() || !userIdStr.matches("\\d+")) {
            req.setAttribute("userUpdatedMessage", "Invalid user ID: must be an integer.");
            req.getRequestDispatcher("WEB-INF/admin-update-user.jsp").forward(req, resp);
            return;
        }

        User user = UserDAO.getUserById(Integer.parseInt(userIdStr));
        if (user == null) {
            req.setAttribute("userUpdatedMessage", "User not found!");
            req.getRequestDispatcher("WEB-INF/admin-update-user.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("userId", userIdStr);
        req.setAttribute("user", user);
        req.setAttribute("userStatus", user.getStatus());
        req.setAttribute("userPrivileges", user.getPrivileges());
        req.setAttribute("pageTitle", "Edit User");
        req.getRequestDispatcher("WEB-INF/admin-update-user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");

        if (userFromSession == null || !"active".equals(userFromSession.getStatus()) || !"admin".equals(userFromSession.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String userIdStr = req.getParameter("user_id");
        if (userIdStr == null || userIdStr.trim().isEmpty() || !userIdStr.matches("\\d+")) {
            req.setAttribute("userUpdated", false);
            req.setAttribute("userUpdatedMessage", "Invalid user ID format: must be an integer.");
            req.getRequestDispatcher("WEB-INF/admin-update-user.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("userId", userIdStr);
        User originalUser = UserDAO.getUserById(Integer.parseInt(userIdStr));
        if (originalUser == null) {
            req.setAttribute("userUpdated", false);
            req.setAttribute("userUpdatedMessage", "User not found!");
            req.getRequestDispatcher("WEB-INF/admin-update-user.jsp").forward(req, resp);
            return;
        }

        String newStatus = req.getParameter("status");
        String newPrivileges = req.getParameter("privileges");

        boolean validationError = false;

        if (newStatus == null || newStatus.trim().isEmpty() || !("active".equals(newStatus) || "inactive".equals(newStatus) || "locked".equals(newStatus))) {
            req.setAttribute("statusError", true);
            req.setAttribute("statusMessage", "Invalid status value.");
            validationError = true;
        }

        if (newPrivileges == null || newPrivileges.trim().isEmpty() || !("subscriber".equals(newPrivileges) || "user".equals(newPrivileges) || "premium".equals(newPrivileges) || "admin".equals(newPrivileges))) {
            req.setAttribute("privilegesError", true);
            req.setAttribute("privilegesMessage", "Invalid privileges value.");
            validationError = true;
        }

        if (validationError) {
            req.setAttribute("userUpdated", false);
            req.setAttribute("userUpdatedMessage", "Please correct the errors below.");
            req.getRequestDispatcher("WEB-INF/admin-update-user.jsp").forward(req, resp);
            return;
        }

        User updatedUser = new User();
        updatedUser.setUserId(Integer.parseInt(userIdStr));
        updatedUser.setStatus(newStatus);
        updatedUser.setPrivileges(newPrivileges);

        boolean success = UserDAO.updateUserAdmin(originalUser, updatedUser);

        req.setAttribute("pageTitle", "Edit User");
        req.setAttribute("userUpdated", success);
        req.setAttribute("userUpdatedMessage", success ? "User updated successfully." : "Failed to update user.");
        req.getRequestDispatcher("WEB-INF/admin-update-user.jsp").forward(req, resp);
    }
}
