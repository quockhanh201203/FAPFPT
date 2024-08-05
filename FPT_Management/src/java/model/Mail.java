/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Fpt
 */
public class Mail {
    private int mailId;
    private LocalDate date;
    private LocalTime time;
    private String imageUrl;
    private String title;
    private String description;
    private Mail mail;
    private int status;
    private MailType mailType;

    public Mail() {
    }

    public Mail(int mailId, LocalDate date, LocalTime time, String imageUrl, String title, String description, Mail mail, int status, MailType mailType) {
        this.mailId = mailId;
        this.date = date;
        this.time = time;
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.mail = mail;
        this.status = status;
        this.mailType = mailType;
    }

    public int getMailId() {
        return mailId;
    }

    public void setMailId(int mailId) {
        this.mailId = mailId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public MailType getMailType() {
        return mailType;
    }

    public void setMailType(MailType mailType) {
        this.mailType = mailType;
    }
    
    
    
    
}
