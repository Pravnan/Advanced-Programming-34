package com.example.vehiclereservationapp.model;

import java.sql.Timestamp;

public class HelpDocumentation {
    private int helpID;
    private String title;

    public int getHelpID() {
        return helpID;
    }

    public void setHelpID(int helpID) {
        this.helpID = helpID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    private String content;
    private int adminID;
    private Timestamp createdAt;
}
