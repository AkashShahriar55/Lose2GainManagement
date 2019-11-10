package com.example.lose2gainmanagement.ui.home;

public class QuickNotificationInfo {

    private String clint_id;
    private String clinet_name;
    private String last_follow_up_date;

    public QuickNotificationInfo(String clint_id, String clinet_name, String last_follow_up_date) {
        this.clint_id = clint_id;
        this.clinet_name = clinet_name;
        this.last_follow_up_date = last_follow_up_date;
    }

    public String getClint_id() {
        return clint_id;
    }

    public String getClinet_name() {
        return clinet_name;
    }

    public String getLast_follow_up_date() {
        return last_follow_up_date;
    }

    public void setClint_id(String clint_id) {
        this.clint_id = clint_id;
    }

    public void setClinet_name(String clinet_name) {
        this.clinet_name = clinet_name;
    }

    public void setLast_follow_up_date(String last_follow_up_date) {
        this.last_follow_up_date = last_follow_up_date;
    }
}
