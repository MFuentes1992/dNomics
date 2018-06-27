package com.example.markf.dnomics;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.nio.Buffer;
import java.util.ArrayList;

/**
 * Created by markf on 24/05/2018.
 */

public class Functions {

    public String stringifyPerson(PersonTO person){

        StringBuffer buffer = new StringBuffer();
        buffer.append("ID:"+person.getPersonID()+"\n");
        buffer.append("Name:"+person.getName()+"\n");
        buffer.append("Surname:"+person.getSurName()+"\n");
        buffer.append("UniqueID:"+person.getUniqueID()+"\n");
        buffer.append("UserName:"+person.getUserName()+"\n");
        buffer.append("Password:"+person.getPassword()+"\n");
        buffer.append("email:"+person.getEmail()+"\n");
        buffer.append("Country Alpha ID:"+person.getCountry_alphaID()+"\n");
        buffer.append("BirthDate:"+person.getBirthDate()+"\n");
        buffer.append("Create Date:"+person.getCreateDate()+"\n");
        buffer.append("Update Date:"+person.getUpdateDate()+"\n");
        buffer.append("Img_url:"+person.getImgUrl()+"\n");
        buffer.append("Estatus:"+person.getEstatusID()+"\n");
        return buffer.toString();
    }

    public String stringifyAllPersons(ArrayList<PersonTO> persons){
        StringBuffer buffer = new StringBuffer();
        for(int i = 0; i < persons.size(); i++){
            buffer.append("ID:"+persons.get(i).getPersonID()+"\n");
            buffer.append("Name:"+persons.get(i).getName()+"\n");
            buffer.append("Surname:"+persons.get(i).getSurName()+"\n");
            buffer.append("UniqueID:"+persons.get(i).getUniqueID()+"\n");
            buffer.append("UserName:"+persons.get(i).getUserName()+"\n");
            buffer.append("password:"+persons.get(i).getPassword()+"\n");
            buffer.append("email:"+persons.get(i).getEmail()+"\n");
            buffer.append("Country Alpha ID:"+persons.get(i).getCountry_alphaID()+"\n");
            buffer.append("BirthDate:"+persons.get(i).getBirthDate()+"\n");
            buffer.append("Create Date:"+persons.get(i).getCreateDate()+"\n");
            buffer.append("Update Date:"+persons.get(i).getUpdateDate()+"\n");
            buffer.append("Img_url:"+persons.get(i).getImgUrl()+"\n");
            buffer.append("Estatus:"+persons.get(i).getEstatusID()+"\n");
            buffer.append("\n");
        }
        return buffer.toString();
    }

    public String stringifyCountry(CountryTO country){
        StringBuffer buffer = new StringBuffer();
        buffer.append("ID:"+country.getCountryID()+"\n");
        buffer.append("Country Alpha:"+country.getCountryAlpha()+"\n");
        buffer.append("Name:"+country.getName()+"\n");
        buffer.append("Description:"+country.getDescription()+"\n");
        buffer.append("Longitud:"+country.getLongitud()+"\n");
        buffer.append("Latitud:"+country.getLatituded()+"\n");
        return buffer.toString();
    }

    public String stringifyAllCountries(ArrayList<CountryTO> countries){
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < countries.size(); i++){
            buffer.append("ID:"+countries.get(i).getCountryID()+"\n");
            buffer.append("Country Alpha:"+countries.get(i).getCountryAlpha()+"\n");
            buffer.append("Name:"+countries.get(i).getName()+"\n");
            buffer.append("Description:"+countries.get(i).getDescription()+"\n");
            buffer.append("Longitud:"+countries.get(i).getLongitud()+"\n");
            buffer.append("Latitud:"+countries.get(i).getLatituded()+"\n");
            buffer.append("\n");
        }
        return buffer.toString();
    }

    public String emptyFieldMsg(View v){
        return v.getContext().getString(R.string.emptyField);
    }

    public void showMessage(View v, String title, String message){
        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
        alert.setCancelable(true);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.show();
    }

    public boolean hasEmptyFields(LinearLayout layout){
        boolean flag = false;
        for(int i = 0; i < layout.getChildCount(); i++){
            if(layout.getChildAt(i) instanceof EditText && ((EditText) layout.getChildAt(i)).getText().toString().equals("")){
                ShapeDrawable shape = new ShapeDrawable(new RectShape());
                shape.getPaint().setColor(Color.RED);
                shape.getPaint().setStyle(Paint.Style.STROKE);
                shape.getPaint().setStrokeWidth(3);
                ((EditText) layout.getChildAt(i)).setBackground(shape);
                flag = true;
            }
        }
        return flag;
    }

    public PersonTO fillPerson(String nombre, String surname, String uniqueID, String usuario, String password, String email, int country, String birthDate, String createDate, String updateDate, String imgurl){
        PersonTO person = new PersonTO();
        person.setName(nombre);
        person.setSurName(surname);
        person.setUniqueID(uniqueID);
        person.setUserName(usuario);
        person.setPassword(password);
        person.setEmail(email);
        person.setCountry_alphaID(country);
        person.setBirthDate(birthDate);
        person.setCreateDate(createDate);
        person.setImgUrl(imgurl);
        person.setUpdateDate(updateDate);
        return person;
    }

}
