/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author Fpt
 */
public class Account {
    private int accountId;
    private Staff staff;
    private String userName;
    private String password;
    private String oldPass1;
    private String oldPass2;
    private String oldPass3;
    private String oldPass4;
    private Timestamp lastPassChange;
    private Timestamp timeLogin;
    private int flag;
    private int ban;

    public Account() {
    }

    public Account(int accountId, Staff staff, String userName, String password, String oldPass1, String oldPass2, String oldPass3, String oldPass4, Timestamp lastPassChange, Timestamp timeLogin, int flag, int ban) {
        this.accountId = accountId;
        this.staff = staff;
        this.userName = userName;
        this.password = password;
        this.oldPass1 = oldPass1;
        this.oldPass2 = oldPass2;
        this.oldPass3 = oldPass3;
        this.oldPass4 = oldPass4;
        this.lastPassChange = lastPassChange;
        this.timeLogin = timeLogin;
        this.flag = flag;
        this.ban = ban;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPass1() {
        return oldPass1;
    }

    public void setOldPass1(String oldPass1) {
        this.oldPass1 = oldPass1;
    }

    public String getOldPass2() {
        return oldPass2;
    }

    public void setOldPass2(String oldPass2) {
        this.oldPass2 = oldPass2;
    }

    public String getOldPass3() {
        return oldPass3;
    }

    public void setOldPass3(String oldPass3) {
        this.oldPass3 = oldPass3;
    }

    public String getOldPass4() {
        return oldPass4;
    }

    public void setOldPass4(String oldPass4) {
        this.oldPass4 = oldPass4;
    }

    public Timestamp getLastPassChange() {
        return lastPassChange;
    }

    public void setLastPassChange(Timestamp lastPassChange) {
        this.lastPassChange = lastPassChange;
    }

    public Timestamp getTimeLogin() {
        return timeLogin;
    }

    public void setTimeLogin(Timestamp timeLogin) {
        this.timeLogin = timeLogin;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getBan() {
        return ban;
    }

    public void setBan(int ban) {
        this.ban = ban;
    }

    
    
    
    
}
