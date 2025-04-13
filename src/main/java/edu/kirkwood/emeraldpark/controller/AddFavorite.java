package edu.kirkwood.emeraldpark.controller;

import edu.kirkwood.emeraldpark.model.FavoriteDAO;
import edu.kirkwood.toystore.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/add-favorite")
public class AddFavorite extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            session.setAttribute("flashMessageWarning", "Please log in to add to your favorites.");
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        int trailId = Integer.parseInt(req.getParameter("trailId"));
        FavoriteDAO.addFavorite(user.getUserId(), trailId);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}