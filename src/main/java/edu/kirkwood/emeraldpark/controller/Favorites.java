package edu.kirkwood.emeraldpark.controller;

import edu.kirkwood.emeraldpark.model.FavoriteDAO;
import edu.kirkwood.emeraldpark.model.Trail;
import edu.kirkwood.emeraldpark.model.TrailDAO;
import edu.kirkwood.toystore.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.*;

@WebServlet("/favorites")
public class Favorites extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("activeUser");

        if (user == null) {
            session.setAttribute("flashMessageWarning", "Please log in to view your favorites.");
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Set<Integer> favoriteTrailIds = new HashSet<>(FavoriteDAO.getFavoritesByUser(user.getUserId()));
        List<Trail> allFavorites = new ArrayList<>();

        for (Integer trailId : favoriteTrailIds) {
            Trail trail = TrailDAO.getTrail(String.valueOf(trailId));
            if (trail != null) {
                allFavorites.add(trail);
            }
        }

        req.setAttribute("favoriteTrailIds", favoriteTrailIds);
        req.setAttribute("trails", allFavorites);
        req.setAttribute("activeUser", user);
        req.setAttribute("pageTitle", "My Favorites");

        req.getRequestDispatcher("WEB-INF/favorites.jsp").forward(req, resp);
    }
}
