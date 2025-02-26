package edu.kirkwood.toystore.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet("/signout")
public class Signout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(); // Get existing session
        session.removeAttribute("activeUser");
        session.setAttribute("flashMessageWarning", "You are logged out. See you next time!");
        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/")); // Redirects to the home page
    }
}
