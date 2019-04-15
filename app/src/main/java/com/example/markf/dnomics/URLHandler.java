package com.example.markf.dnomics;

public class URLHandler {

    private String url = "http://dnomicscloudeagle.com/";
    private String addUser = "add_user.php";
    private String getUser = "get_user.php";

    public URLHandler(){

    }

    public String urlAddUser(){
        return url + addUser;
    }

    public String urlGetUser(){
        return url + getUser;
    }
}
