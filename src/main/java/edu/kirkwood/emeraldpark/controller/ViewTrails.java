package edu.kirkwood.emeraldpark.controller;

import edu.kirkwood.emeraldpark.model.Trail;
import edu.kirkwood.emeraldpark.model.TrailDAO;
import edu.kirkwood.toystore.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(value="/view-trails")
public class ViewTrails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        User userFromSession = (User)session.getAttribute("activeUser");
//        if(userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
//            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
        List<Trail> trails = TrailDAO.getTrails();
        req.setAttribute("trails", trails);
        req.setAttribute("pageTitle", "Trails");
        req.getRequestDispatcher("WEB-INF/view-trails.jsp").forward(req, resp);

    }
}
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Trail> trails = TrailDAO.getTrails();
//        req.setAttribute("trails", trails);
//        req.getRequestDispatcher("WEB-INF/view-trails.jsp").forward(req, resp);
//        // forwarding to jsp, inside of web inf because customers should not be able to put in the link, they must access the servlet
//    }
//}
//
