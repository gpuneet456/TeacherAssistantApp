package com.example.madproject;

public class attendance_card_item {


    private String usn;
    private String name;
    private String attended;

    public attendance_card_item(String usn, String name,String attended) {
        this.usn = usn;
        this.name = name;
        this.attended=attended;
    }

    public attendance_card_item() {

    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttended() {
        return attended;
    }

    public void setAttended(String attended) {
        this.attended = attended;
    }
}