package edu.kirkwood.toystore.controller;

import edu.kirkwood.toystore.model.User;
import edu.kirkwood.toystore.model.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class AdminUsers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User)session.getAttribute("activeUser");
        if(userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
//            session.setAttribute("flashMessageWarning", "You do not have permission to access this page");
//            resp.sendRedirect("login");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // throw new ServletException("This is an error");
       List<User> users = UserDAO.getAll();
       req.setAttribute("users", users);
       req.setAttribute("pageTitle", "Users");
       req.getRequestDispatcher("WEB-INF/admin-users.jsp").forward(req, resp);
    }
}