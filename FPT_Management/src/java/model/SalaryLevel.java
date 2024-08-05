/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Fpt
 */
public class SalaryLevel {
    private int salaryLevelId;
    private String salaryLevelName;
    private float salaryCoefficient;

    public SalaryLevel() {
    }

    public SalaryLevel(int salaryLevelId, String salaryLevelName, float salaryCoefficient) {
        this.salaryLevelId = salaryLevelId;
        this.salaryLevelName = salaryLevelName;
        this.salaryCoefficient = salaryCoefficient;
    }

    public int getSalaryLevelId() {
        return salaryLevelId;
    }

    public void setSalaryLevelId(int salaryLevelId) {
        this.salaryLevelId = salaryLevelId;
    }

    public String getSalaryLevelName() {
        return salaryLevelName;
    }

    public void setSalaryLevelName(String salaryLevelName) {
        this.salaryLevelName = salaryLevelName;
    }

    public float getSalaryCoefficient() {
        return salaryCoefficient;
    }

    public void setSalaryCoefficient(float salaryCoefficient) {
        this.salaryCoefficient = salaryCoefficient;
    }
    
    
}
