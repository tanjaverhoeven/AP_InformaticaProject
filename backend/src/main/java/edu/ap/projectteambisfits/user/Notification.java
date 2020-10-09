package edu.ap.projectteambisfits.user;

public class Notification {
    private String defectId;
    private boolean isRead;

    public Notification(String defectId) {
        this.defectId = defectId;
        this.isRead = false;
    }

    public String getDefectId() {
        return this.defectId;
    }

    public void setDefectId(String defectId) {
        this.defectId = defectId;
    }

    public boolean isIsRead() {
        return this.isRead;
    }

    public boolean getIsRead() {
        return this.isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }
    
}