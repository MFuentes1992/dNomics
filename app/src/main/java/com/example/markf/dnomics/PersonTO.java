package com.example.markf.dnomics;

/**
 * Created by markf on 15/05/2018.
 */

public class PersonTO {

    /*Variables*/

    private int userID;
    private String first_name;
    private String last_name;
    private String user_password;
    private String user_email;
    private String currency_name;
    private String create_date;
    private String update_date;
    private String img_url;
    private String status_name;
    private String create_source;

    public PersonTO(){
        userID = 0;
        first_name = "";
        last_name = "";
        user_password = "";
        user_email = "";
        currency_name = "";
        create_date = "";
        update_date = "";
        img_url = "";
        status_name = "";
        create_source = "";
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getCurrency_name() {
        return currency_name;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getCreate_source() {
        return create_source;
    }

    public void setCreate_source(String create_source) {
        this.create_source = create_source;
    }
}
