/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Fpt
 */
public class Staff {

    private int staffId;
    private String firstName;
    private String lastName;
    private boolean gender;
    private String homeTown;
    private Date birthDate;
    private String phoneNumber;
    private SalaryLevel salaryLevel;
    private Department department;
    private String imageurl;
    private Account account;
    private String email;
    private int otp;

    public Staff() {
    }

    public Staff(int staffId, String firstName, String lastName, boolean gender, String homeTown, Date birthDate, String phoneNumber, SalaryLevel salaryLevel, Department department, String imageurl, Account account, String email, int otp) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.homeTown = homeTown;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.salaryLevel = salaryLevel;
        this.department = department;
        this.imageurl = imageurl;
        this.account = account;
        this.email = email;
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public SalaryLevel getSalaryLevel() {
        return salaryLevel;
    }

    public void setSalaryLevel(SalaryLevel salaryLevel) {
        this.salaryLevel = salaryLevel;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}


