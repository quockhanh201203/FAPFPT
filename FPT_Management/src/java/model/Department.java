/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Fpt
 */
public class Department {
    private int departmentId;
    private String departmentName;
    private String departmentLocation;
    private String departmentPhoneNumber;

    public Department() {
    }

    public Department(int departmentId, String departmentName, String departmentLocation, String departmentPhoneNumber) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentLocation = departmentLocation;
        this.departmentPhoneNumber = departmentPhoneNumber;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentLocation() {
        return departmentLocation;
    }

    public void setDepartmentLocation(String departmentLocation) {
        this.departmentLocation = departmentLocation;
    }

    public String getDepartmentPhoneNumber() {
        return departmentPhoneNumber;
    }

    public void setDepartmentPhoneNumber(String departmentPhoneNumber) {
        this.departmentPhoneNumber = departmentPhoneNumber;
    }
    
    
}
