package edu.kirkwood.emeraldpark.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/checkout")
public class Checkout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("activeUser") == null) {
            resp.sendRedirect("login");
            return;
        }

        String amount = (String) session.getAttribute("donationAmount");
        req.setAttribute("donationAmount", amount);
        req.setAttribute("pageTitle", "Checkout");
        req.getRequestDispatcher("WEB-INF/checkout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] billingInfo = new String[8];
        billingInfo[0] = "Marc";
        billingInfo[1] = "Hauschildt";
        billingInfo[2]  = "marc.hauschildt@kirkwood.edu";
        billingInfo[3]  = "6301 Kirkwood Blvd SW";
        billingInfo[4]  = "Cedar Rapids";
        billingInfo[5]  = "IA";
        billingInfo[6]  = "52404";


    }
}
