package com.example.markf.dnomics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by markf on 15/05/2018.
 */

public class DatabaseModel extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dnomics_open.db";
    public DatabaseModel(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strQueryCountry = "CREATE TABLE country (country_alphaID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, country_alpha TEXT NOT NULL, name TEXT NOT NULL);";
        String strQueryUserStatus = "CREATE TABLE user_status (statusID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL);";
        String strQueryReportStatus = "CREATE TABLE report_status (statusID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL); ";
        String strQueryRoutingProcess = "CREATE TABLE routing_process(routingID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, personID INTEGER NOT NULL, reportID INTEGER NOT NULL);";
        String strQueryReportHeader = "CREATE TABLE report_header (reportID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, personID INTEGER NOT NULL, report_name TEXT NOT NULL, report_date TEXT NOT NULL, report_location TEXT NOT NULL, report_number TEXT NOT NULL, report_total REAL NOT NULL, report_status INTEGER NOT NULL);";
        String strQueryReportLineItem = "CREATE TABLE line_item (lineitemID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, reportID INTEGER NOT NULL, lineitem_date TEXT NOT NULL, lineitem_total REAL NOT NULL, lineitem_purpose TEXT NOT NULL, lineitem_merchant TEXT NOT NULL, lineitem_allocation TEXT NOT NULL);";
        String strQueryPerson = "CREATE TABLE person (personID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, surname TEXT NOT NULL, uniqueID TEXT NOT NULL, username TEXT NOT NULL, password TEXT NOT NULL, email TEXT NOT NULL, country_alphaID INTEGER NOT NULL, birth_date TEXT NOT NULL, create_date TEXT NOT NULL, update_date TEXT NOT NULL, img_data BLOB, estatus INTEGER NOT NULL);";

        db.execSQL(strQueryReportHeader);
        db.execSQL(strQueryReportLineItem);
        db.execSQL(strQueryCountry);
        db.execSQL(strQueryPerson);
        db.execSQL(strQueryUserStatus);
        db.execSQL(strQueryReportStatus);
        db.execSQL(strQueryRoutingProcess);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS country");
        db.execSQL("DROP TABLE IF EXISTS person");
        onCreate(db);
    }


    public long insertCountryAsync(SQLiteDatabase db, String country_alpha, String name){
        String tableName = "country";
        ContentValues contentValues = new ContentValues();
        contentValues.put("country_alpha", country_alpha);
        contentValues.put("name", name);
        long _res = db.insert(tableName, null, contentValues);
        return _res;
    }

    public ArrayList<CountryTO> getAllCountryData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ArrayList<CountryTO> countries = new ArrayList<>();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM country", null);
        if(res.getCount() > 0 ){
            while (res.moveToNext()){
                CountryTO country = new CountryTO();
                country.setCountryID(Integer.parseInt(res.getString(0)));
                country.setCountryAlpha(res.getString(1));
                country.setName(res.getString(2));
                country.setDescription(res.getString(3));
                country.setLongitud(Double.parseDouble(res.getString(4)));
                country.setLatituded(Double.parseDouble(res.getString(5)));
                countries.add(country);
            }
        }
        return countries;
    }

    public CountryTO getCountry(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM country WHERE country_alphaID = 1", null);
        CountryTO country = new CountryTO();
        if(res.getCount() > 0){
            res.moveToNext();
            country.setCountryID(Integer.parseInt(res.getString(0)));
            country.setCountryAlpha(res.getString(1));
            country.setName(res.getString(2));
            country.setDescription(res.getString(3));
            country.setLongitud(Double.parseDouble(res.getString(4)));
            country.setLatituded(Double.parseDouble(res.getString(5)));
        }
        return country;
    }

    public boolean updatePerson ( PersonTO _person){
        boolean res = false;
        String tableName = "person";
        PersonTO person = _person;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("name", person.getName());
        contentValue.put("surname", person.getSurName());
        contentValue.put("uniqueID", person.getUniqueID());
        contentValue.put("username", person.getUserName());
        contentValue.put("password", person.getPassword());
        contentValue.put("email", person.getEmail());
        contentValue.put("country_alphaID", person.getCountry_alphaID());
        contentValue.put("birth_date", person.getBirthDate().toString());
        contentValue.put("create_date", person.getCreateDate().toString());
        contentValue.put("img_data", person.getImgData());
        contentValue.put("update_date", person.getUpdateDate().toString());
        contentValue.put("estatus", person.getEstatusID());
        long _res = db.update(tableName, contentValue, "personID = "+_person.getPersonID(), null);
        if(_res != -1){res=true;}
        return res;
    }

    public boolean updateReport( ReportTO report ){
        boolean res = false;
        String table = "report_header";
        ReportTO _report = report;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("personID", report.getPersonID());
        contentValue.put("report_name", report.getReportName());
        contentValue.put("report_date", report.getReportDate());
        contentValue.put("report_location", report.getReportLocation());
        contentValue.put("report_number", report.getReportNumber());
        long _res = db.update(table, contentValue, "reportID = "+report.getReportID(), null);
        if(_res != -1) { res = true; }
        return res;
    }

    public boolean insertPersonAsync (SQLiteDatabase db, PersonTO _person){
        boolean res = false;
        String tableName = "person";
        PersonTO person = _person;
        ContentValues contentValue = new ContentValues();
        contentValue.put("name", person.getName());
        contentValue.put("surname", person.getSurName());
        contentValue.put("uniqueID", person.getUniqueID());
        contentValue.put("username", person.getUserName());
        contentValue.put("password", person.getPassword());
        contentValue.put("email", person.getEmail());
        contentValue.put("country_alphaID", person.getCountry_alphaID());
        contentValue.put("birth_date", person.getBirthDate().toString());
        contentValue.put("create_date", person.getCreateDate().toString());
        contentValue.put("img_data", person.getImgData());
        contentValue.put("update_date", person.getUpdateDate().toString());
        contentValue.put("estatus", person.getEstatusID());
        long _res = db.insert(tableName, null, contentValue);
        if(_res != -1){res=true;}
        return res;
    }

    public long insertReport(ReportTO report){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("personID", report.getPersonID());
        contentValue.put("report_name", report.getReportName());
        contentValue.put("report_date", report.getReportDate());
        contentValue.put("report_location", report.getReportLocation());
        contentValue.put("report_number", report.getReportNumber());
        contentValue.put("report_total", report.getReportTotal());
        contentValue.put("report_status", report.getReportStatus());
        long _res = db.insert("report_header", null, contentValue);
        return _res;
    }

    public long insertLineItem(LineItemTO lineItem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();
        contentValues.put("reportID", lineItem.getReportID());
        contentValues.put("lineitem_date", lineItem.getLineItemDate());
        contentValues.put("lineitem_total", lineItem.getLineItemTotal());
        contentValues.put("lineitem_purpose", lineItem.getLineItemPurpose());
        contentValues.put("lineitem_merchant", lineItem.getLineItemMerchant());
        contentValues.put("lineitem_allocation", lineItem.getLineItemAllocation());
        long _res = db.insert("line_item",null, contentValues);
        return _res;
    }

    public ReportTO getReportByID(long ID){
        ReportTO report = new ReportTO();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM report_header WHERE reportID = "+ID, null);
        if(res.getCount() > 0 ){
            res.moveToNext();
            report.setReportID(Long.parseLong(res.getString(0)));
            report.setPersonID(Long.parseLong(res.getString(1)));
            report.setReportName(res.getString(2));
            report.setReportDate(res.getString(3));
            report.setReportLocation(res.getString(4));
            report.setReportNumber(res.getString(5));
            report.setReportTotal(Double.parseDouble(res.getString(6)));
        }
        return report;
    }

    public PersonTO getPersonByUserName(String userName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM person WHERE username = '"+userName+"'", null);
        PersonTO person = new PersonTO();
        if(res.getCount() > 0){
            res.moveToNext();
            person.setPersonID(Integer.parseInt(res.getString(0)));
            person.setName(res.getString(1));
            person.setSurName(res.getString(2));
            person.setUniqueID(res.getString(3));
            person.setUserName(res.getString(4));
            person.setPassword(res.getString(5));
            person.setEmail(res.getString(6));
            person.setCountry_alphaID(Integer.parseInt(res.getString(7)));
            person.setBirthDate(res.getString(8));
            person.setCreateDate(res.getString(9));
            person.setUpdateDate(res.getString(10));
            person.setImgData(res.getBlob(11));
            person.setEstatusID(Integer.parseInt(res.getString(12)));
        }
        return person;
    }

    public PersonTO getPersonByUserNamePass(String username, String pass){
        PersonTO person = new PersonTO();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM person WHERE username = '"+username+"' and password = '"+pass+"'", null);
        if(res.getCount() > 0){
            res.moveToNext();
            person.setPersonID(Integer.parseInt(res.getString(0)));
            person.setName(res.getString(1));
            person.setSurName(res.getString(2));
            person.setUniqueID(res.getString(3));
            person.setUserName(res.getString(4));
            person.setPassword(res.getString(5));
            person.setEmail(res.getString(6));
            person.setCountry_alphaID(Integer.parseInt(res.getString(7)));
            person.setBirthDate(res.getString(8));
            person.setCreateDate(res.getString(9));
            person.setUpdateDate(res.getString(10));
            person.setImgData(res.getBlob(11));
            person.setEstatusID(Integer.parseInt(res.getString(12)));
        }
        return person;
    }

    public PersonTO getPersonByUserNamePass(SQLiteDatabase sqLiteDatabase, String username, String pass){
        PersonTO person = new PersonTO();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM person WHERE username = '"+username+"' and password = '"+pass+"'", null);
        if(res.getCount() > 0){
            res.moveToNext();
            person.setPersonID(Integer.parseInt(res.getString(0)));
            person.setName(res.getString(1));
            person.setSurName(res.getString(2));
            person.setUniqueID(res.getString(3));
            person.setUserName(res.getString(4));
            person.setPassword(res.getString(5));
            person.setEmail(res.getString(6));
            person.setCountry_alphaID(Integer.parseInt(res.getString(7)));
            person.setBirthDate(res.getString(8));
            person.setCreateDate(res.getString(9));
            person.setUpdateDate(res.getString(10));
            person.setImgData(res.getBlob(11));
            person.setEstatusID(Integer.parseInt(res.getString(12)));
        }
        return person;
    }

    public PersonTO getPersonByEmail(String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM person WHERE email = '"+email+"'", null);
        PersonTO person = new PersonTO();
        if(res.getCount() > 0){
            res.moveToNext();
            person.setPersonID(Integer.parseInt(res.getString(0)));
            person.setName(res.getString(1));
            person.setSurName(res.getString(2));
            person.setUniqueID(res.getString(3));
            person.setUserName(res.getString(4));
            person.setPassword(res.getString(5));
            person.setEmail(res.getString(6));
            person.setCountry_alphaID(Integer.parseInt(res.getString(7)));
            person.setBirthDate(res.getString(8));
            person.setCreateDate(res.getString(9));
            person.setUpdateDate(res.getString(10));
            person.setImgData(res.getBlob(11));
            person.setEstatusID(Integer.parseInt(res.getString(12)));
        }
        return person;
    }


    public PersonTO getPersonByID(int personID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM person WHERE personID = "+personID+"", null);
        PersonTO person = new PersonTO();
        if(res.getCount() > 0){
            res.moveToNext();
            person.setPersonID(Integer.parseInt(res.getString(0)));
            person.setName(res.getString(1));
            person.setSurName(res.getString(2));
            person.setUniqueID(res.getString(3));
            person.setUserName(res.getString(4));
            person.setPassword(res.getString(5));
            person.setEmail(res.getString(6));
            person.setCountry_alphaID(Integer.parseInt(res.getString(7)));
            person.setBirthDate(res.getString(8));
            person.setCreateDate(res.getString(9));
            person.setUpdateDate(res.getString(10));
            person.setImgData(res.getBlob(11));
            person.setEstatusID(Integer.parseInt(res.getString(12)));
        }
        return person;
    }


    public ArrayList<PersonTO> getAllPersonData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM person", null);
        ArrayList<PersonTO> personArray = new ArrayList<PersonTO>();
        while(res.moveToNext()){
            PersonTO person = new PersonTO();
            person.setPersonID(Integer.parseInt(res.getString(0)));
            person.setName(res.getString(1));
            person.setSurName(res.getString(2));
            person.setUniqueID(res.getString(3));
            person.setUserName(res.getString(4));
            person.setPassword(res.getString(5));
            person.setEmail(res.getString(6));
            person.setCountry_alphaID(Integer.parseInt(res.getString(7)));
            person.setBirthDate(res.getString(8));
            person.setCreateDate(res.getString(9));
            person.setUpdateDate(res.getString(10));
            person.setImgData(res.getBlob(11));
            person.setEstatusID(Integer.parseInt(res.getString(12)));
            personArray.add(person);
        }
        return personArray;
    }

    public ArrayList<ReportTO> getAllDraftReports(int personID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM report_header where personID ="+personID+" AND report_status = 1", null);
        ArrayList<ReportTO> reportArray = new ArrayList<ReportTO>();
        while(res.moveToNext()){
            ReportTO report = new ReportTO();
            report.setReportID(Integer.parseInt(res.getString(0)));
            report.setPersonID(Integer.parseInt(res.getString(1)));
            report.setReportName(res.getString(2));
            report.setReportDate(res.getString(3));
            report.setReportLocation(res.getString(4));
            report.setReportNumber(res.getString(5));
            report.setReportTotal(Double.parseDouble(res.getString(6)));
            reportArray.add(report);
        }
        return reportArray;
    }
}
