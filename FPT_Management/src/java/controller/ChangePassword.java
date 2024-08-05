/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author Admin
 */
public class ChangePassword extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangePassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassword at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account currentUser = (Account) session.getAttribute("currentUser");
        request.setAttribute("currentUser", currentUser);
        request.getRequestDispatcher("view/changepassword.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type_raw = request.getParameter("type");
        HttpSession session = request.getSession();
        String emailForgotpassword = (String) session.getAttribute("email");
        String idCurrentUser_raw = request.getParameter("id");
        String passwordEmailForgotpassword = request.getParameter("passwordEmailForgotpassword");
        String currrentUserPassword = request.getParameter("currrentUserPassword");
        String passwordCurrentUser = request.getParameter("passwordCurrentUser");
        if (type_raw != null && !type_raw.isEmpty()) {
            int type = Integer.parseInt(type_raw);
            if (type == 0) {
                int id = StaffDAO.INSTANCE.getStaffIdByEmail(emailForgotpassword);
                try {
                    AccountDAO.INSTANCE.changePassword(id, passwordEmailForgotpassword);
                } catch (Exception ex) {
                    Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.getSession().removeAttribute("email");
                response.sendRedirect("login");
            } else {
                int id = Integer.parseInt(idCurrentUser_raw);
                try {
                    if (AccountDAO.INSTANCE.checkCorrectCurrentPassword(id, currrentUserPassword)) {
                        AccountDAO.INSTANCE.changePassword(id, passwordCurrentUser);
                        request.getSession().removeAttribute("currentUser");
                        response.sendRedirect("login");
                    } else {
                        request.setAttribute("errorCurrentPassword", "Current password is incorrect!");
                        request.getRequestDispatcher("view/changepassword.jsp").forward(request, response);
                    }
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            request.getRequestDispatcher("view/accessdenied.jsp").forward(request, response);
        }
    }

}
