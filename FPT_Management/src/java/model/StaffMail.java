/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Fpt
 */
public class StaffMail {
    private int staffMailId;
    private Mail mail;
    private Staff fromStaff;
    private Staff toStaff;

    public StaffMail() {
    }

    public StaffMail(int staffMailId, Mail mail, Staff fromStaff, Staff toStaff) {
        this.staffMailId = staffMailId;
        this.mail = mail;
        this.fromStaff = fromStaff;
        this.toStaff = toStaff;
    }

    public int getStaffMailId() {
        return staffMailId;
    }

    public void setStaffMailId(int staffMailId) {
        this.staffMailId = staffMailId;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public Staff getFromStaff() {
        return fromStaff;
    }

    public void setFromStaff(Staff fromStaff) {
        this.fromStaff = fromStaff;
    }

    public Staff getToStaff() {
        return toStaff;
    }

    public void setToStaff(Staff toStaff) {
        this.toStaff = toStaff;
    }
    
    
}
