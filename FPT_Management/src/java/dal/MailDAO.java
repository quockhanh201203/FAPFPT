/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author Fpt
 */
public class MailDAO extends DBContext {

    private Connection con;
    public static MailDAO INSTANCE = new MailDAO();

    public MailDAO() {
        if (INSTANCE == null) {
            try {
                con = new DBContext().getConnection();
            } catch (Exception e) {
                Logger.getLogger(MailDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            }
        }
    }

    public ArrayList<StaffMail> getAllMailByStaffId(int staffId, String type) {
        ArrayList<StaffMail> listStaffMail = new ArrayList<>();
        String sql = "SELECT MT.*, M.*, SM.*, AC1.STAFF_ID, AC1.USER_NAME, AC2.USER_NAME FROM STAFF_MAIL SM\n"
                + "JOIN STAFF S1 ON S1.STAFF_ID = SM.FROM_STAFF_ID\n"
                + "JOIN ACCOUNT AC1 ON AC1.STAFF_ID = S1.STAFF_ID\n"
                + "JOIN STAFF S2 ON S2.STAFF_ID = SM.TO_STAFF_ID\n"
                + "JOIN ACCOUNT AC2 ON AC2.STAFF_ID = S2.STAFF_ID\n"
                + "JOIN MAIL M ON M.MAIL_ID = SM.MAIL_ID\n"
                + "JOIN MAIL_TYPE MT ON MT.MAIL_TYPE_ID = M.MAIL_TYPE_ID\n"
                + "WHERE SM." + type + "_STAFF_ID = ? ORDER BY M.DATE DESC, M.TIME DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, staffId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MailType mailType = new MailType();
                mailType.setMailTypeId(rs.getInt(1));
                mailType.setMailTypeName(rs.getString(2));
                mailType.setMailTypeDescription(rs.getString(3));
                Mail mail = new Mail();
                mail.setMailId(rs.getInt(4));
                mail.setDate(rs.getDate(5).toLocalDate());
                mail.setTime(rs.getTime(6).toLocalTime());
                mail.setImageUrl(rs.getString(7));
                mail.setTitle(rs.getString(8));
                mail.setDescription(rs.getString(9));
                mail.setStatus(rs.getInt(11));
                mail.setMailType(mailType);
                Staff st1 = new Staff();
                st1.setStaffId(rs.getInt(15));
                Account acc1 = new Account();
                acc1.setUserName(rs.getString(18));
                st1.setAccount(acc1);
                Staff st2 = new Staff();
                st2.setStaffId(rs.getInt(16));
                Account acc2 = new Account();
                acc2.setUserName(rs.getString(19));
                st2.setAccount(acc2);
                StaffMail staffMail = new StaffMail();
                staffMail.setStaffMailId(rs.getInt(13));
                staffMail.setFromStaff(st1);
                staffMail.setToStaff(st2);
                staffMail.setMail(mail);

                listStaffMail.add(staffMail);
            }

            return listStaffMail;
        } catch (Exception e) {
            Logger.getLogger(MailDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return null;
    }

    public ArrayList<MailType> getAllMailType() {
        ArrayList<MailType> listMailType = new ArrayList<>();
        String sql = "SELECT * FROM MAIL_TYPE";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MailType mailType = new MailType();
                mailType.setMailTypeId(rs.getInt(1));
                mailType.setMailTypeName(rs.getString(2));
                mailType.setMailTypeDescription(rs.getString(3));
                listMailType.add(mailType);
            }

            return listMailType;
        } catch (Exception e) {
            Logger.getLogger(MailDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return null;
    }

    public int insertMail(String date, String time, String imageUrl, String title, String description, int status, int mailTypeId) {
        String sql = "INSERT INTO MAIL ([DATE], [TIME], IMAGE_URL, TITLE, [DESCRIPTION], [STATUS], MAIL_TYPE_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int generatedId = -1;

        try {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, date);
            ps.setString(2, time);
            ps.setString(3, imageUrl);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setInt(6, status);
            ps.setInt(7, mailTypeId);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(MailDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return generatedId;
    }
    
    public void insertStaffMail(int mailId, int fromStaffId, int toStaffId) {
        String sql = "INSERT INTO STAFF_MAIL (MAIL_ID, FROM_STAFF_ID, TO_STAFF_ID) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mailId);
            ps.setInt(2, fromStaffId);
            ps.setInt(3, toStaffId);

            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(MailDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void main(String[] args) {
        ArrayList<StaffMail> listStaffMail = MailDAO.INSTANCE.getAllMailByStaffId(2, "TO");
        for (StaffMail staffMail : listStaffMail) {
            System.out.println(staffMail.getFromStaff().getAccount().getUserName());
        }
    }

}
