package edu.kirkwood.emeraldpark.controller;

import edu.kirkwood.toystore.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/donate")
public class Donate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("activeUser");

//        if (user == null) {
//            session.setAttribute("flashMessageWarning", "Please log in to donate and receive premium content.");
//            resp.sendRedirect(req.getContextPath() + "/login");
//            return;
//        }

        req.setAttribute("pageTitle", "Donate");
        req.getRequestDispatcher("WEB-INF/donate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
//        if (session == null || session.getAttribute("activeUser") == null) {
//            resp.sendRedirect("login");
//            return;
//        }

        String selectedOption = req.getParameter("donationOption");
        String customAmountParam = req.getParameter("customAmount");
        double amount = 0;
        boolean hasError = false;

        if (selectedOption == null) {
            req.setAttribute("amountError", "Please select or enter a donation amount.");
            hasError = true;
        } else if ("custom".equals(selectedOption)) {
            if (customAmountParam == null || customAmountParam.isEmpty()) {
                req.setAttribute("amountError", "Please enter a custom amount.");
                hasError = true;
            } else {
                try {
                    amount = Double.parseDouble(customAmountParam);
                    if (amount < 5) {
                        req.setAttribute("amountError", "Minimum donation is $5.");
                        hasError = true;
                    }
                } catch (NumberFormatException e) {
                    req.setAttribute("amountError", "Invalid custom amount.");
                    hasError = true;
                }
            }
        } else {
            try {
                amount = Double.parseDouble(selectedOption);
                if (amount < 5) {
                    req.setAttribute("amountError", "Minimum donation is $5.");
                    hasError = true;
                }
            } catch (NumberFormatException e) {
                req.setAttribute("amountError", "Invalid amount selected.");
                hasError = true;
            }
        }

        if (hasError) {
            req.setAttribute("pageTitle", "Donate");
            req.getRequestDispatcher("WEB-INF/donate.jsp").forward(req, resp);
            return;
        }

        session.setAttribute("donationAmount", String.format("%.2f", amount));
        resp.sendRedirect("checkout");

    }
}
