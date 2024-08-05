/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Department;

/**
 *
 * @author Fpt
 */
public class AddNewAccountByAdmin extends HttpServlet {

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
            out.println("<title>Servlet AddNewAccountByAdmin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewAccountByAdmin at " + request.getContextPath() + "</h1>");
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
        List<Department> departments = DepartmentDAO.INSTANCE.getAllDepartments();
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("view/createnewaccount.jsp").forward(request, response);
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
        String fname = request.getParameter("firstname");
        String lname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String flag_raw = request.getParameter("flag");
        String departmentId_raw = request.getParameter("department");
        int departmentId = 0;
        int flag = 0;
        try {
            departmentId = Integer.parseInt(departmentId_raw);
            flag = Integer.parseInt(flag_raw);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        boolean isUsernameExists = AccountDAO.INSTANCE.checkExistedUser(username);
        boolean isPhoneExists = StaffDAO.INSTANCE.checkExistedPhoneNumber(phone);

        if (!isUsernameExists && !isPhoneExists) {
            int staffId = StaffDAO.INSTANCE.addNewStaffByAdmin(fname, lname, address, phone, departmentId);
            AccountDAO.INSTANCE.addAccount(staffId, username, password, flag);
            response.sendRedirect("admin");
            return;
        }

        if (AccountDAO.INSTANCE.checkExistedUser(username)) {
            request.setAttribute("errorUsername", "Username is already existed!");
        }

        if (StaffDAO.INSTANCE.checkExistedPhoneNumber(phone)) {
            request.setAttribute("errorPhone", "Phone number is already existed!");
        }
        
        request.setAttribute("fname", fname);
        request.setAttribute("lname", lname);
        request.setAttribute("user", username);
        request.setAttribute("flag", flag);
        request.setAttribute("phone", phone);
        request.setAttribute("address", address);
        request.setAttribute("departmentId", departmentId);

        doGet(request, response); 

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
