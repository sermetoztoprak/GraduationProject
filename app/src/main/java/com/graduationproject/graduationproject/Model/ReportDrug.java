package com.graduationproject.graduationproject.Model;

public class ReportDrug {

    private int id;
    private String Medicine;
    private String Clock;
    private String TimeArrival;
    private String Note;

    public ReportDrug(int id, String medicine, String clock, String timeArrival, String note) {
        this.id = id;
        Medicine = medicine;
        Clock = clock;
        TimeArrival = timeArrival;
        Note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicine() {
        return Medicine;
    }

    public void setMedicine(String medicine) {
        Medicine = medicine;
    }

    public String getClock() {
        return Clock;
    }

    public void setClock(String clock) {
        Clock = clock;
    }

    public String getTimeArrival() {
        return TimeArrival;
    }

    public void setTimeArrival(String timeArrival) {
        TimeArrival = timeArrival;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }
}
