/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Department;
import model.SalaryLevel;
import model.Staff;

/**
 *
 * @author Admin
 */
public class StaffDAO {

    private Connection con;
    public static StaffDAO INSTANCE = new StaffDAO();

    public StaffDAO() {
        if (INSTANCE == null) {
            try {
                con = new DBContext().getConnection();
            } catch (Exception e) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            }
        }
    }

    public int addNewStaffByAdmin(String fname, String lname, String homeTown, String phoneNumber, int departmentId) {
        int insertedID = 0;
        String sql = "INSERT INTO STAFF\n"
                + "(FIRST_NAME, LAST_NAME, HOME_TOWN, PHONE_NUMBER, DEPARTMENT_ID, IMAGE_URL)\n"
                + "VALUES (?,?,?,?,?, 'userdefault.jpg')";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, homeTown);
            ps.setString(4, phoneNumber);
            ps.setInt(5, departmentId);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insertedID = rs.getInt(1);
            }

        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            e.printStackTrace();
        }
        return insertedID;
    }

    public boolean checkExistedPhoneNumber(String phone) {
        String sql = "SELECT USER_NAME FROM STAFF WHERE PHONE_NUMBER = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return false;
    }

    public void updateStaffByAdmin(int staffId, String firstName, String lastName, int gender, String homeTown, String dateOfBirth, String phoneNumber, int departmentId, float salary_level) {
        String sql = "UPDATE [dbo].[STAFF]\n"
                + "   SET [FIRST_NAME] = ?\n"
                + "      ,[LAST_NAME] = ?\n"
                + "      ,[GENDER] = ?\n"
                + "      ,[HOME_TOWN] = ?\n"
                + "      ,[BIRTH_DATE] = ?\n"
                + "      ,[PHONE_NUMBER] = ?\n"
                + "      ,[SALARY_LEVEL_ID] = ?\n"
                + "      ,[DEPARTMENT_ID] = ?\n"
                + " WHERE STAFF_ID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setInt(3, gender);
            ps.setString(4, homeTown);
            ps.setString(5, dateOfBirth);
            ps.setString(6, phoneNumber);
            ps.setFloat(7, salary_level);
            ps.setInt(8, departmentId);
            ps.setInt(9, staffId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkExistedEmail(String email) {
        if ("".equals(email.trim())) {
            return false;
        }
        String sql = "SELECT 1 FROM STAFF WHERE EMAIL = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return false;
    }

    public boolean checkExistedOtp(int otp) {
        String sql = "SELECT 1 FROM STAFF WHERE OTP = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, otp);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return false;
    }

    public int getStaffIdByEmail(String email) {
        String sql = "SELECT STAFF_ID FROM STAFF WHERE EMAIL = ?";
        int STAFF_ID = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                STAFF_ID = rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            e.printStackTrace();
        }
        return STAFF_ID;
    }

    public void updateOtpById(int id, int otp) {
        String sql = "UPDATE STAFF SET OTP = ? WHERE STAFF_ID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, otp);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
    }

    public Staff getStaffById(int staffId) {
        String sql = "SELECT s.STAFF_ID, s.FIRST_NAME, s.LAST_NAME, s.GENDER, s.HOME_TOWN, s.BIRTH_DATE, s.PHONE_NUMBER, s.SALARY_LEVEL_ID, slr.SALARY_LEVEL_NAME, s.DEPARTMENT_ID, d.DEPARTMENT_NAME, d.DEPARTMENT_LOCATION, s.IMAGE_URL, a.ACCOUNT_ID, a.USER_NAME, a.FLAG, a.BAN, a.TIME_LOGIN, s.EMAIL, s.OTP "
                + "FROM STAFF s "
                + "LEFT JOIN ACCOUNT a ON s.STAFF_ID = a.STAFF_ID "
                + "LEFT JOIN DEPARTMENT d ON s.DEPARTMENT_ID = d.DEPARTMENT_ID "
                + "LEFT JOIN SALARY_LEVEL slr ON s.SALARY_LEVEL_ID = slr.SALARY_LEVEL_ID "
                + "WHERE s.STAFF_ID = ?";

        Staff staff = null;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, staffId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                staff = new Staff();
                staff.setStaffId(rs.getInt("STAFF_ID"));
                staff.setFirstName(rs.getString("FIRST_NAME"));
                staff.setLastName(rs.getString("LAST_NAME"));
                staff.setGender(rs.getBoolean("GENDER"));
                staff.setHomeTown(rs.getString("HOME_TOWN"));
                staff.setBirthDate(rs.getDate("BIRTH_DATE"));
                staff.setPhoneNumber(rs.getString("PHONE_NUMBER"));
                staff.setImageurl(rs.getString("IMAGE_URL"));
                staff.setEmail(rs.getString("EMAIL"));
                staff.setOtp(rs.getInt("OTP"));

                SalaryLevel salaryLevel = new SalaryLevel();
                salaryLevel.setSalaryLevelId(rs.getInt("SALARY_LEVEL_ID"));
                salaryLevel.setSalaryLevelName(rs.getString("SALARY_LEVEL_NAME"));
                staff.setSalaryLevel(salaryLevel);

                Department department = new Department();
                department.setDepartmentId(rs.getInt("DEPARTMENT_ID"));
                department.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
                department.setDepartmentLocation(rs.getString("DEPARTMENT_LOCATION"));
                staff.setDepartment(department);

                Account account = new Account();
                account.setAccountId(rs.getInt("ACCOUNT_ID"));
                account.setUserName(rs.getString("USER_NAME"));
                account.setFlag(rs.getInt("FLAG"));
                account.setBan(rs.getInt("BAN"));
                account.setTimeLogin(rs.getTimestamp("TIME_LOGIN"));
                staff.setAccount(account);
            }
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return staff;
    }

    public static void main(String[] args) {
        StaffDAO d = new StaffDAO();
        d.updateOtpById(1, 12345);
    }

}
