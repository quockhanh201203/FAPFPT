/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Fpt
 */
public class MailType {
    private int mailTypeId;
    private String mailTypeName;
    private String mailTypeDescription;

    public MailType() {
    }

    public MailType(int mailTypeId, String mailTypeName, String mailTypeDescription) {
        this.mailTypeId = mailTypeId;
        this.mailTypeName = mailTypeName;
        this.mailTypeDescription = mailTypeDescription;
    }

    public int getMailTypeId() {
        return mailTypeId;
    }

    public void setMailTypeId(int mailTypeId) {
        this.mailTypeId = mailTypeId;
    }

    public String getMailTypeName() {
        return mailTypeName;
    }

    public void setMailTypeName(String mailTypeName) {
        this.mailTypeName = mailTypeName;
    }

    public String getMailTypeDescription() {
        return mailTypeDescription;
    }

    public void setMailTypeDescription(String mailTypeDescription) {
        this.mailTypeDescription = mailTypeDescription;
    }
    
    
}
