package com.example.markf.dnomics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by markf on 15/05/2018.
 */

public class DatabaseModel extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dnomics_open.db";
    public DatabaseModel(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    SQLiteDatabase db = this.getWritableDatabase();

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strQueryCountry = "CREATE TABLE country (country_alphaID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, country_alpha TEXT NOT NULL, name TEXT NOT NULL);";

        String strQueryPerson = "CREATE TABLE person (personID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, surname TEXT NOT NULL, uniqueID TEXT NOT NULL, username TEXT NOT NULL, password TEXT NOT NULL, email TEXT NOT NULL, country_alphaID INTEGER NOT NULL, birth_date TEXT NOT NULL, create_date TEXT NOT NULL, update_date TEXT NOT NULL, img_url TEXT NOT NULL, estatus INTEGER NOT NULL);";

        db.execSQL(strQueryCountry);
        db.execSQL(strQueryPerson);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS country");
        db.execSQL("DROP TABLE IF EXISTS person");
        onCreate(db);
    }

    public long insertCountry(String country_alpha, String name, String description, String lat, String _long){
        String tableName = "country";
        SQLiteDatabase db = this.getWritableDatabase();
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

    public boolean insertPerson ( PersonTO _person){
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
        contentValue.put("img_url", person.getImgUrl());
        contentValue.put("update_date", person.getUpdateDate().toString());
        contentValue.put("estatus", person.getEstatusID());
        long _res = db.insert(tableName, null, contentValue);
        if(_res != -1){res=true;}
        return res;
    }

    public PersonTO getPerson(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM person WHERE personID = 1", null);
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
            person.setImgUrl(res.getString(11));
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
            person.setImgUrl(res.getString(11));
            person.setEstatusID(Integer.parseInt(res.getString(12)));
            personArray.add(person);
        }
        return personArray;
    }
}
