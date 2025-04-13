package edu.kirkwood.emeraldpark.controller;

import edu.kirkwood.emeraldpark.model.FavoriteDAO;
import edu.kirkwood.toystore.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/remove-favorite")
public class RemoveFavorite extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            session.setAttribute("flash", "Please log in to remove favorites.");
            resp.sendRedirect("login.jsp");
            return;
        }

        int trailId = Integer.parseInt(req.getParameter("trailId"));
        FavoriteDAO.removeFavorite(user.getUserId(), trailId);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}