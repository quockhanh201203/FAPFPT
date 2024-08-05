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
import model.SalaryLevel;

/**
 *
 * @author Admin
 */
public class SalaryDAO {

    private Connection con;
    public static SalaryDAO INSTANCE = new SalaryDAO();

    public SalaryDAO() {
        if (INSTANCE == null) {
            try {
                con = new DBContext().getConnection();
            } catch (Exception e) {
                Logger.getLogger(SalaryDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            }
        }
    }

    public List<SalaryLevel> getAllSalaryLevels() {
        List<SalaryLevel> salaryLevels = new ArrayList<>();
        String sql = "SELECT * FROM SALARY_LEVEL";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SalaryLevel slr_level = new SalaryLevel();
                slr_level.setSalaryLevelId(rs.getInt(1));
                slr_level.setSalaryLevelName(rs.getString(2));
                slr_level.setSalaryCoefficient(rs.getFloat(3));
                salaryLevels.add(slr_level);
            }
            return salaryLevels;
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return null;
    }

}
