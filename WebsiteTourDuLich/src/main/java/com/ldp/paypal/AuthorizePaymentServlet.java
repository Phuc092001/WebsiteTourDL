/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.paypal;

import com.ldp.pojos.Bill;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.paypal.base.rest.PayPalRESTException;

/**
 *
 * @author ACER
 */
@WebServlet("authorize_payment")
public class AuthorizePaymentServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    
    public AuthorizePaymentServlet() {
        
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String product = request.getParameter("product");
        String subtotal = request.getParameter("subtotal");
        String shipping = request.getParameter("shipping");
        String tax = request.getParameter("tax");
        String total = request.getParameter("total");
        
        Bill bill = new Bill(product, subtotal, shipping, tax, total);
        
         try {
            PaypalService paymentServices = new PaypalService();
            String approvalLink = paymentServices.authorizePayment(bill);

            response.sendRedirect(approvalLink);

        } catch (PayPalRESTException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();
            request.getRequestDispatcher("khongTimThay.jsp").forward(request, response);
        }
    }
    
}
