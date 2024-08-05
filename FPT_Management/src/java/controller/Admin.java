/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.DepartmentDAO;
import dal.SalaryDAO;
import dal.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.List;
import model.Account;
import model.Department;
import model.Pagination;
import model.SalaryLevel;

/**
 *
 * @author Fpt
 */
public class Admin extends HttpServlet {

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
            out.println("<title>Servlet Admin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Admin at " + request.getContextPath() + "</h1>");
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
        String key = request.getParameter("key");
        int cp = 1;
        request.setAttribute("searchKey", key);
        if (key != null && !key.isEmpty()) {
            List<Account> searchedAccounts = AccountDAO.INSTANCE.searchAccount(key.trim());
            String cp_raw = request.getParameter("cp");
            if (cp_raw != null) {
                cp = Integer.parseInt(cp_raw);
            }
            Pagination page = new Pagination(searchedAccounts.size(), 8, cp);
            session.setAttribute("page", page);
            List<Account> pagedAccounts = AccountDAO.INSTANCE.getAccountsByPage(page.getCurrentPage(), 8, searchedAccounts);
            request.setAttribute("accList", pagedAccounts);
        } else {
            if (request.getParameter("cp") != null) {
                cp = Integer.parseInt(request.getParameter("cp"));
            }
            Pagination page = new Pagination(AccountDAO.INSTANCE.getAllInformationStaff().size(), 8, cp);
            session.setAttribute("page", page);
            List<Account> accounts = AccountDAO.INSTANCE.getAccountsByPage(page.getCurrentPage(), 8, AccountDAO.INSTANCE.getAllInformationStaff());
            request.setAttribute("accList", accounts);
        }
        List<Department> departments = DepartmentDAO.INSTANCE.getAllDepartments();
        request.setAttribute("departments", departments);

        List<SalaryLevel> salaryLevels = SalaryDAO.INSTANCE.getAllSalaryLevels();
        request.setAttribute("salaryLevels", salaryLevels);

        request.setAttribute("currentUser", currentUser);
        request.getRequestDispatcher("view/admin.jsp").forward(request, response);
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
        String key = request.getParameter("key");
        String type_raw = request.getParameter("type");
        String userId_raw = request.getParameter("accountId");
        String currentPage_raw = request.getParameter("cp");
        String role_raw = request.getParameter("flag");
        String isBanned_raw = request.getParameter("ban");

        String fname = request.getParameter("firstName");
        String lname = request.getParameter("lastName");
        String gender_raw = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String address = request.getParameter("homeTown");
        String departmentId_raw = request.getParameter("department");
        String dob = request.getParameter("dob");
        String salaryId_raw = request.getParameter("salary");

        if (type_raw != null && userId_raw != null && currentPage_raw != null) {
            int type = Integer.parseInt(type_raw);
            int user_id = Integer.parseInt(userId_raw);
            int currentPage = Integer.parseInt(currentPage_raw);

            if (type == 0) {
                if (role_raw != null && !role_raw.isEmpty()) {
                    int role = Integer.parseInt(role_raw);
                    AccountDAO.INSTANCE.updateRoleAccountById(user_id, role);
                }
            }

            if (type == 1) {
                if (isBanned_raw != null && !isBanned_raw.isEmpty()) {
                    int isBanned = Integer.parseInt(isBanned_raw);
                    AccountDAO.INSTANCE.updateStatusAccountById(user_id, isBanned);
                }
            }

            if (type == 2) {
                if (fname != null && !fname.isEmpty()
                        && lname != null && !lname.isEmpty()
                        && gender_raw != null && !gender_raw.isEmpty()
                        && phone != null && !phone.isEmpty()
                        && address != null && !address.isEmpty()
                        && departmentId_raw != null && !departmentId_raw.isEmpty()
                        && dob != null && !dob.isEmpty()
                        && salaryId_raw != null && !salaryId_raw.isEmpty()) {
                    int departmentId = Integer.parseInt(departmentId_raw);
                    float salary = Float.parseFloat(salaryId_raw);
                    int gender = Integer.parseInt(gender_raw);
                    StaffDAO.INSTANCE.updateStaffByAdmin(user_id, fname, lname, gender, address, dob, phone, departmentId, salary);
                }
            }

            if (key != null && !key.isEmpty()) {
                response.sendRedirect("admin?cp=" + currentPage + "&key=" + URLEncoder.encode(key, "UTF-8"));
            } else {
                response.sendRedirect("admin?cp=" + currentPage);
            }
            return;
        }

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
