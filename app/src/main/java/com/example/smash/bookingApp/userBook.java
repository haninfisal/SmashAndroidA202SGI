package com.example.smash.bookingApp;

public class userBook {

    String courtName;
    String fName;
    String phone;
    String dateText;
    String timeText;
    String hourInput;

    public userBook() {
    }

    public userBook(String courtName, String fName, String phone, String dateText, String timeText, String hourInput) {
        this.courtName = courtName;
        this.fName = fName;
        this.phone = phone;
        this.dateText = dateText;
        this.timeText = timeText;
        this.hourInput = hourInput;
    }

    public String getFname() {
        return fName;
    }

    public String getPhone() {
        return phone;
    }

    public String getCourtName() {
        return courtName;
    }

    public String getDateText() {
        return dateText;
    }

    public String getTimeText() {
        return timeText;
    }

    public String getHourInput() {
        return hourInput;
    }
}
