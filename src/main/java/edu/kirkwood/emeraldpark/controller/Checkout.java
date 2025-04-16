package edu.kirkwood.emeraldpark.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.authorize.api.contract.v1.CreateTransactionResponse;
import net.authorize.api.contract.v1.MessageTypeEnum;
import net.authorize.api.contract.v1.TransactionResponse;
import edu.kirkwood.shared.authorize_net.ChargeCreditCard;


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
        billingInfo[0] = req.getParameter("firstName");
        billingInfo[1] = req.getParameter("lastName");
        billingInfo[2] = req.getParameter("address");
        billingInfo[3] = req.getParameter("city");
        billingInfo[4] = req.getParameter("state");
        billingInfo[5] = req.getParameter("zip");
        billingInfo[6] = "USA";
        billingInfo[7] = "";

        String email = req.getParameter("email");

        String[] ccInfo = new String[3];
        ccInfo[0] = req.getParameter("cc-number");
        ccInfo[1] = req.getParameter("cc-expiration");
        ccInfo[2] = req.getParameter("cc-cvv");

        HttpSession session = req.getSession();
        String amountStr = (String) session.getAttribute("donationAmount");
        double amount = 0;

        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException | NullPointerException e) {
            session.setAttribute("flashMessageError", "Invalid donation amount.");
            req.setAttribute("pageTitle", "Checkout");
            req.getRequestDispatcher("WEB-INF/checkout.jsp").forward(req, resp);
            return;
        }
        boolean errorFound = false;

        if (billingInfo[0] == null || billingInfo[0].isBlank()) {
            req.setAttribute("firstNameError", "First name is required.");
            errorFound = true;
        }

        if (billingInfo[1] == null || billingInfo[1].isBlank()) {
            req.setAttribute("lastNameError", "Last name is required.");
            errorFound = true;
        }

        if (email == null || email.isBlank() || !email.contains("@")) {
            req.setAttribute("emailError", "A valid email is required.");
            errorFound = true;
        }

        if (ccInfo[0] == null || ccInfo[0].length() < 13) {
            req.setAttribute("ccNumberError", "Credit card number is required.");
            errorFound = true;
        }

        if (ccInfo[1] == null || ccInfo[1].isBlank()) {
            req.setAttribute("ccExpirationError", "Expiration is required.");
            errorFound = true;
        }

        if (ccInfo[2] == null || ccInfo[2].length() < 3) {
            req.setAttribute("ccCvvError", "CVV is required.");
            errorFound = true;
        }

        req.setAttribute("firstName", billingInfo[0]);
        req.setAttribute("lastName", billingInfo[1]);
        req.setAttribute("email", email);
        req.setAttribute("address", billingInfo[2]);
        req.setAttribute("city", billingInfo[3]);
        req.setAttribute("state", billingInfo[4]);
        req.setAttribute("zip", billingInfo[5]);


        if (errorFound) {
            req.setAttribute("pageTitle", "Checkout");
            req.setAttribute("donationAmount", session.getAttribute("donationAmount"));
            req.getRequestDispatcher("WEB-INF/checkout.jsp").forward(req, resp);
            return;
        }



        CreateTransactionResponse response = (CreateTransactionResponse)
                ChargeCreditCard.run(amount, ccInfo, billingInfo, email);

        if (response != null && response.getMessages().getResultCode() == MessageTypeEnum.OK) {
            TransactionResponse result = response.getTransactionResponse();
            if (result.getMessages() != null) {
                session.setAttribute("flashMessageSuccess", "Your donation has been processed. Thank you!");
            } else {
                if (result.getErrors() != null && !result.getErrors().getError().isEmpty()) {
                    String errorText = result.getErrors().getError().get(0).getErrorText();
                    session.setAttribute("flashMessageError", "Transaction failed: " + errorText);
                } else {
                    session.setAttribute("flashMessageError", "Transaction failed for an unknown reason.");
                }
            }
        } else {
            session.setAttribute("flashMessageError", "Payment could not be processed.");
        }

        req.setAttribute("pageTitle", "Checkout");
        req.getRequestDispatcher("WEB-INF/checkout.jsp").forward(req, resp);
    }

}
