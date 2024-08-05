/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Department;
import model.Pagination;
import model.SalaryLevel;
import model.Staff;

/**
 *
 * @author Fpt
 */
public class AccountDAO extends DBContext {

    private Connection con;
    public static AccountDAO INSTANCE = new AccountDAO();

    public AccountDAO() {
        if (INSTANCE == null) {
            try {
                con = new DBContext().getConnection();
            } catch (Exception e) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            }
        }
    }

    public Account checkLogin(String username, String password) {
        Account acc = null;
        String sql = "SELECT a.ACCOUNT_ID, a.USER_NAME, a.PASS_WORD, a.OLD_PASS_1, a.OLD_PASS_2, a.OLD_PASS_3, a.OLD_PASS_4, "
                + "a.LAST_PASS_CHANGE, a.TIME_LOGIN, a.FLAG, a.BAN, "
                + "s.STAFF_ID, s.FIRST_NAME, s.LAST_NAME, s.GENDER, s.HOME_TOWN, s.BIRTH_DATE, s.PHONE_NUMBER, s.IMAGE_URL, "
                + "s.DEPARTMENT_ID, s.EMAIL, s.OTP,"
                + "d.DEPARTMENT_NAME, d.DEPARTMENT_LOCATION, d.DEPARTMENT_PHONE_NUMBER "
                + "FROM ACCOUNT a "
                + "JOIN STAFF s ON a.STAFF_ID = s.STAFF_ID "
                + "JOIN DEPARTMENT d ON s.DEPARTMENT_ID = d.DEPARTMENT_ID "
                + "WHERE a.USER_NAME = ? AND a.PASS_WORD = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                acc = new Account();
                acc.setAccountId(rs.getInt("ACCOUNT_ID"));
                acc.setUserName(rs.getString("USER_NAME"));
                acc.setPassword(rs.getString("PASS_WORD"));
                acc.setOldPass1(rs.getString("OLD_PASS_1"));
                acc.setOldPass2(rs.getString("OLD_PASS_2"));
                acc.setOldPass3(rs.getString("OLD_PASS_3"));
                acc.setOldPass4(rs.getString("OLD_PASS_4"));
                acc.setLastPassChange(rs.getTimestamp("LAST_PASS_CHANGE"));
                acc.setTimeLogin(rs.getTimestamp("TIME_LOGIN"));
                acc.setFlag(rs.getInt("FLAG"));
                acc.setBan(rs.getInt("BAN"));

                Staff st = new Staff();
                st.setStaffId(rs.getInt("STAFF_ID"));
                st.setFirstName(rs.getString("FIRST_NAME"));
                st.setLastName(rs.getString("LAST_NAME"));
                st.setGender(rs.getBoolean("GENDER"));
                st.setHomeTown(rs.getString("HOME_TOWN"));
                st.setBirthDate(rs.getDate("BIRTH_DATE"));
                st.setPhoneNumber(rs.getString("PHONE_NUMBER"));
                st.setImageurl(rs.getString("IMAGE_URL"));
                st.setEmail(rs.getString("EMAIL"));
                st.setOtp(rs.getInt("OTP"));

                Department dt = new Department();
                dt.setDepartmentId(rs.getInt("DEPARTMENT_ID"));
                dt.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
                dt.setDepartmentLocation(rs.getString("DEPARTMENT_LOCATION"));
                dt.setDepartmentPhoneNumber(rs.getString("DEPARTMENT_PHONE_NUMBER"));

                st.setDepartment(dt);
                acc.setStaff(st);
            }

            return acc;
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, "Error in checkLogin method", e);
        }
        return null;
    }

    public void updateStaff(int staffId, String firstName, String lastName, int gender, String homeTown, String dateOfBirth, String phoneNumber) {
        String sql = "UPDATE STAFF SET FIRST_NAME = ?, \n"
                + "LAST_NAME = ?,  GENDER = ?, BIRTH_DATE = ?,\n"
                + "HOME_TOWN = ?, PHONE_NUMBER = ? WHERE STAFF_ID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setInt(3, gender);
            ps.setString(4, dateOfBirth);
            ps.setString(5, homeTown);
            ps.setString(6, phoneNumber);
            ps.setInt(7, staffId);

            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
    }

    public ArrayList<Account> getAllStaffByUserName(String username) {
        ArrayList<Account> listacc = new ArrayList<>();
        Account acc = null;
        String sql = "SELECT * FROM ACCOUNT a \n"
                + "JOIN STAFF s on a.STAFF_ID = s.STAFF_ID\n"
                + "JOIN DEPARTMENT d ON s.DEPARTMENT_ID = d.DEPARTMENT_ID\n"
                + "WHERE a.USER_NAME LIKE '%" + username + "%'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                acc = new Account();
                acc.setAccountId(rs.getInt(1));
                Staff st = new Staff();
                st.setStaffId(rs.getInt(2));
                st.setFirstName(rs.getString(14));
                st.setLastName(rs.getString(15));
                st.setGender(rs.getBoolean(16));
                st.setHomeTown(rs.getString(17));
                st.setBirthDate(rs.getDate(18));
                st.setPhoneNumber(rs.getString(19));
                st.setImageurl(rs.getString(22));

                Department dt = new Department();
                dt.setDepartmentId(rs.getInt(23));
                dt.setDepartmentName(rs.getString(24));
                dt.setDepartmentLocation(rs.getString(25));
                dt.setDepartmentPhoneNumber(rs.getString(26));
                st.setDepartment(dt);

                SalaryLevel sla = new SalaryLevel();
                sla.setSalaryLevelId(rs.getInt(20));
                st.setSalaryLevel(sla);
                acc.setStaff(st);

                acc.setUserName(rs.getString(3));
                acc.setPassword(rs.getString(4));
                acc.setOldPass1(rs.getString(5));
                acc.setOldPass2(rs.getString(6));
                acc.setOldPass3(rs.getString(7));
                acc.setOldPass4(rs.getString(8));
                acc.setLastPassChange(rs.getTimestamp(9));
                acc.setTimeLogin(rs.getTimestamp(10));
                acc.setFlag(rs.getInt(11));
                acc.setBan(rs.getInt(12));

                listacc.add(acc);
            }

            return listacc;
        } catch (Exception e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return null;
    }

    public Account getAccountByAccountId(int accId) {
        Account acc = null;
        String sql = "SELECT * FROM ACCOUNT a \n"
                + "JOIN STAFF s on a.STAFF_ID = s.STAFF_ID\n"
                + "JOIN DEPARTMENT d ON s.DEPARTMENT_ID = d.DEPARTMENT_ID\n"
                + "WHERE a.ACCOUNT_ID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                acc = new Account();
                acc.setAccountId(rs.getInt(1));
                Staff st = new Staff();
                st.setStaffId(rs.getInt(2));
                st.setFirstName(rs.getString(14));
                st.setLastName(rs.getString(15));
                st.setGender(rs.getBoolean(16));
                st.setHomeTown(rs.getString(17));
                st.setBirthDate(rs.getDate(18));
                st.setPhoneNumber(rs.getString(19));
                st.setImageurl(rs.getString(22));

                Department dt = new Department();
                dt.setDepartmentId(rs.getInt(23));
                dt.setDepartmentName(rs.getString(24));
                dt.setDepartmentLocation(rs.getString(25));
                dt.setDepartmentPhoneNumber(rs.getString(26));
                st.setDepartment(dt);

                SalaryLevel sla = new SalaryLevel();
                sla.setSalaryLevelId(rs.getInt(20));
                st.setSalaryLevel(sla);
                acc.setStaff(st);

                acc.setUserName(rs.getString(3));
                acc.setPassword(rs.getString(4));
                acc.setOldPass1(rs.getString(5));
                acc.setOldPass2(rs.getString(6));
                acc.setOldPass3(rs.getString(7));
                acc.setOldPass4(rs.getString(8));
                acc.setLastPassChange(rs.getTimestamp(9));
                acc.setTimeLogin(rs.getTimestamp(10));
                acc.setFlag(rs.getInt(11));
                acc.setBan(rs.getInt(12));
            }

            return acc;
        } catch (Exception e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return null;
    }

    // admin
    public List<Account> getAllInformationStaff() {
        List<Account> listAccounts = new ArrayList<>();
        String sql = "SELECT a.ACCOUNT_ID, a.USER_NAME, s.FIRST_NAME, s.LAST_NAME, d.DEPARTMENT_NAME,\n"
                + "d.DEPARTMENT_LOCATION, s.HOME_TOWN, s.IMAGE_URL, a.FLAG, a.BAN, a.TIME_LOGIN,\n"
                + "s.BIRTH_DATE, s.GENDER, s.PHONE_NUMBER, s.SALARY_LEVEL_ID, slr.SALARY_LEVEL_NAME, "
                + "d.DEPARTMENT_ID, s.STAFF_ID\n"
                + "FROM STAFF s\n"
                + "LEFT JOIN ACCOUNT a ON s.STAFF_ID = a.STAFF_ID\n"
                + "LEFT JOIN DEPARTMENT d ON s.DEPARTMENT_ID = d.DEPARTMENT_ID\n"
                + "LEFT JOIN SALARY_LEVEL slr ON s.SALARY_LEVEL_ID = slr.SALARY_LEVEL_ID";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setAccountId(rs.getInt(1));
                a.setUserName(rs.getString(2));
                a.setFlag(rs.getInt(9));
                a.setBan(rs.getInt(10));
                a.setTimeLogin(rs.getTimestamp(11));
                Staff s = new Staff();
                s.setStaffId(rs.getInt(18));
                s.setFirstName(rs.getString(3));
                s.setLastName(rs.getString(4));
                s.setGender(rs.getBoolean(13));
                s.setBirthDate(rs.getDate(12));
                s.setHomeTown(rs.getString(7));
                s.setImageurl(rs.getString(8));
                s.setPhoneNumber(rs.getString(14));
                Department d = new Department();
                d.setDepartmentId(rs.getInt(17));
                d.setDepartmentName(rs.getString(5));
                d.setDepartmentLocation(rs.getString(6));
                SalaryLevel slr = new SalaryLevel();
                slr.setSalaryLevelId(rs.getInt(15));
                slr.setSalaryLevelName(rs.getString(16));

                s.setSalaryLevel(slr);
                s.setDepartment(d);
                a.setStaff(s);
                listAccounts.add(a);
            }
            return listAccounts;
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return null;
    }

    public List<Account> searchAccount(String key) {
        List<Account> accounts = new Vector<>();
        String sql = "SELECT a.ACCOUNT_ID, a.USER_NAME, s.FIRST_NAME, s.LAST_NAME, d.DEPARTMENT_NAME,\n"
                + "d.DEPARTMENT_LOCATION, s.HOME_TOWN, s.IMAGE_URL, a.FLAG, a.BAN, a.TIME_LOGIN,\n"
                + "s.BIRTH_DATE, s.GENDER, s.PHONE_NUMBER, s.SALARY_LEVEL_ID, slr.SALARY_LEVEL_NAME, "
                + "d.DEPARTMENT_ID, s.STAFF_ID\n"
                + "FROM STAFF s\n"
                + "LEFT JOIN ACCOUNT a ON s.STAFF_ID = a.STAFF_ID\n"
                + "LEFT JOIN DEPARTMENT d ON s.DEPARTMENT_ID = d.DEPARTMENT_ID\n"
                + "LEFT JOIN SALARY_LEVEL slr ON s.SALARY_LEVEL_ID = slr.SALARY_LEVEL_ID\n"
                + "WHERE a.USER_NAME LIKE ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Account a = new Account();
                a.setAccountId(rs.getInt(1));
                a.setUserName(rs.getString(2));
                a.setFlag(rs.getInt(9));
                a.setBan(rs.getInt(10));
                a.setTimeLogin(rs.getTimestamp(11));

                Staff s = new Staff();
                s.setStaffId(rs.getInt(18));
                s.setFirstName(rs.getString(3));
                s.setLastName(rs.getString(4));
                s.setGender(rs.getBoolean(13));
                s.setBirthDate(rs.getDate(12));
                s.setHomeTown(rs.getString(7));
                s.setImageurl(rs.getString(8));
                s.setPhoneNumber(rs.getString(14));

                Department d = new Department();
                d.setDepartmentId(rs.getInt(17));
                d.setDepartmentName(rs.getString(5));
                d.setDepartmentLocation(rs.getString(6));

                SalaryLevel slr = new SalaryLevel();
                slr.setSalaryLevelId(rs.getInt(15));
                slr.setSalaryLevelName(rs.getString(16));

                s.setSalaryLevel(slr);
                s.setDepartment(d);
                a.setStaff(s);
                accounts.add(a);
            }
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return accounts;
    }

    public List<Account> getAccountsByPage(int currentPage, int pageLimit, List<Account> allAccounts) {
        Pagination pagination = new Pagination(allAccounts.size(), pageLimit, currentPage);
        List<Account> productsOnPage = new Vector<>();
        for (int i = pagination.getStartItem(); i <= pagination.getLastItem() && i < allAccounts.size(); i++) {
            productsOnPage.add(allAccounts.get(i));
        }
        return productsOnPage;
    }

    public void updateRoleAccountById(int id, int role) {
        String sql = "UPDATE ACCOUNT SET FLAG = ? WHERE ACCOUNT_ID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, role);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
    }

    public void updateStatusAccountById(int id, int ban) {
        String sql = "UPDATE ACCOUNT SET BAN = ? WHERE ACCOUNT_ID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ban);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
    }

    public void addAccount(int staffId, String username, String password, int flag) {
        String sql = "INSERT INTO ACCOUNT (STAFF_ID, USER_NAME, PASS_WORD, FLAG, BAN)"
                + "VALUES (?, ?, ?, ?, 0)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, staffId);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setInt(4, flag);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
    }

    public void updateTimeLogin(int accountId, Timestamp loginTime) {
        String sql = "UPDATE ACCOUNT SET TIME_LOGIN = ? WHERE ACCOUNT_ID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setTimestamp(1, loginTime);
            ps.setInt(2, accountId);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean checkSuspendedAccount(int accountId) {
        String sql = "SELECT BAN FROM ACCOUNT WHERE ACCOUNT_ID = ? AND BAN = 0";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return false;
    }

    public boolean checkExistedUser(String username) {
        String sql = "SELECT USER_NAME FROM ACCOUNT WHERE USER_NAME = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return false;
    }

    public void changePassword(int staff_id, String password) throws NoSuchAlgorithmException {
        String sql = "UPDATE ACCOUNT SET PASS_WORD = ? WHERE STAFF_ID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, password);
            ps.setInt(2, staff_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
    }

    public boolean checkCorrectCurrentPassword(int id, String password) throws NoSuchAlgorithmException {
        String sql = "SELECT 1 FROM ACCOUNT WHERE PASS_WORD = ? AND STAFF_ID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, password);
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        AccountDAO.INSTANCE.checkLogin("MinhNQ91", "123");
        System.out.println(AccountDAO.INSTANCE.checkLogin("MinhNQ91", "123"));
    }

}
