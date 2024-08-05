/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.MailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import model.Account;
import model.MailType;
import model.StaffMail;

/**
 *
 * @author Fpt
 */
public class Mail extends HttpServlet {

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
            out.println("<title>Servlet Mail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Mail at " + request.getContextPath() + "</h1>");
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
        ArrayList<StaffMail> listReceiveMail = MailDAO.INSTANCE.getAllMailByStaffId(currentUser.getStaff().getStaffId(), "TO");
        request.setAttribute("listReceiveMail", listReceiveMail);
        ArrayList<StaffMail> listSendMail = MailDAO.INSTANCE.getAllMailByStaffId(currentUser.getStaff().getStaffId(), "FROM");
        request.setAttribute("listSendMail", listSendMail);
        ArrayList<MailType> listMailType = MailDAO.INSTANCE.getAllMailType();
        request.setAttribute("listMailType", listMailType);
        ArrayList<Account> listAccount = AccountDAO.INSTANCE.getAllStaffByUserName("");
        request.setAttribute("listAccount", listAccount);
        request.getRequestDispatcher("view/mail.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Account currentUser = (Account) session.getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String file = request.getParameter("file");
        String tagPerson[] = request.getParameterValues("tagPerson");
        String typeMailId = request.getParameter("typeMailId");
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        String date = currentDate.toString(); // yyyy-MM-dd
        String time = currentTime.toString(); // HH:mm:ss
        if (tagPerson.length != 0) {
            int mailId = MailDAO.INSTANCE.insertMail(date, time, file, title, description, 1, Integer.parseInt(typeMailId));

            for (String string : tagPerson) {
                MailDAO.INSTANCE.insertStaffMail(mailId, currentUser.getStaff().getStaffId(), Integer.parseInt(string));
            }
            doGet(request, response);
        }else{
            request.setAttribute("errMsg", "Please choose person");
            doGet(request, response);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
