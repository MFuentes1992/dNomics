package com.example.markf.dnomics;

import java.util.Date;

/**
 * Created by markf on 15/05/2018.
 */

public class PersonTO {

    /*Variables*/
    private int country_alphaID;
    private int estatusID;
    private int tipoID;
    private int deleted;
    private int personID;

    private String createDate;
    private String updateDate;
    private String birthDate;

    private String name;
    private String surName;
    private String uniqueID;
    private String userName;
    private String password;
    private String email ;
    private byte[] imgData;

    public PersonTO(){

        tipoID = 0;
        deleted = 0;
        personID = 0;
        estatusID = 0;
        country_alphaID = 0;

        birthDate = "";
        createDate = "";
        updateDate = "";

        name = "";
        email = "";
        imgData = null;
        surName = "";
        uniqueID = "";
        userName = "";

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public int getCountry_alphaID() {
        return country_alphaID;
    }

    public void setCountry_alphaID(int country_alphaID) {
        this.country_alphaID = country_alphaID;
    }

    public int getEstatusID() {
        return estatusID;
    }

    public void setEstatusID(int estatusID) {
        this.estatusID = estatusID;
    }

    public int getTipoID() {
        return tipoID;
    }

    public void setTipoID(int tipoID) {
        this.tipoID = tipoID;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getImgData() {
        return imgData;
    }

    public void setImgData(byte[] imgData) {
        this.imgData = imgData;
    }
}
