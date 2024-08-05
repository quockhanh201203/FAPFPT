/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;

/**
 *
 * @author Admin
 */
public class DepartmentDAO {

    private Connection con;
    public static DepartmentDAO INSTANCE = new DepartmentDAO();

    public DepartmentDAO() {
        if (INSTANCE == null) {
            try {
                con = new DBContext().getConnection();
            } catch (Exception e) {
                Logger.getLogger(DepartmentDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            }
        }
    }

    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM DEPARTMENT";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Department department = new Department();
                department.setDepartmentId(rs.getInt(1));
                department.setDepartmentName(rs.getString(2));
                department.setDepartmentLocation(rs.getString(3));
                departments.add(department);
            }
            return departments;
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return null;
    }
    
    public static void main(String[] args) {
        List<Department> d = DepartmentDAO.INSTANCE.getAllDepartments();
        for (Department department : d) {
            System.out.println(department.getDepartmentId());
        }
    }

}
