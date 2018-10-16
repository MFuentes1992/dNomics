package com.example.markf.dnomics;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.provider.FontRequest;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    DatabaseModel dbModel;
    TextView txtTitle;
    TextView iconTitle;
    TextView lblUser;
    TextView lblPassword;
    TextView lblRegistro;
    Button btnLogin;
    /*Button btnAddCountries;
    Button btnAddPerson;
    Button btnViewCountries;
    Button btnViewPersons;
    Button btnVieAllPersons;
    Button btnViewAllCountries;*/

    int countryCounter = 0;
    int personCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set the font for the title bar
        txtTitle = (TextView)findViewById(R.id.txtTitle);
        iconTitle = (TextView)findViewById(R.id.iconTitle);
        iconTitle.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        txtTitle.setTypeface(FontManager.getTypeface(getApplicationContext(),FontManager.RIGHTEOUS));

        //Iconos
        lblUser = (TextView)findViewById(R.id.lblUser);
        lblPassword = (TextView)findViewById(R.id.lblPassword);
        lblRegistro = (TextView)findViewById(R.id.lblRegristro);

        lblUser.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblPassword.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblRegistro.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));

        dbModel =  new DatabaseModel(this);
        //btnAddCountries = (Button)findViewById(R.id.btnAddCountries);
        //btnAddPerson = (Button)findViewById(R.id.btnAddPerson);
        //btnViewCountries = (Button)findViewById(R.id.btnViewCountry);
        //btnViewPersons = (Button)findViewById(R.id.btnViewPerson);
        //btnVieAllPersons = (Button)findViewById(R.id.btnViewAllPersons);
        //btnViewAllCountries = (Button)findViewById(R.id.btnViewAllCountries);

        newEntry();

        //addCountry();
        //addPerson();
        //viewCountry();
        //viewAllCountries();
        //viewPersons();
        //viewAllPersons();
    }

    public void newEntry(){
        lblRegistro.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Intent intent = new Intent(MainActivity.this, Registro_A.class);
                        Intent intent = new Intent(MainActivity.this, Dashboard.class);
                        intent.putExtra("key","");
                        MainActivity.this.startActivity(intent);
                    }
                }
        );
    }

    /*Countries*/
   /* public void addCountry(){
        btnAddCountries.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = dbModel.insertCountry("AP", "Country"+countryCounter,"Description", "0.000001", "0.00002");
                        if(isInserted){
                            Toast.makeText(MainActivity.this, "Data inserted correctly", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Data Not inserted correctly", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
        countryCounter++;
    }

    public void viewCountry(){
        btnViewCountries.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    showMessage("Country", new Functions().stringifyCountry(dbModel.getCountry()));
                    }
                }
        );
    }

    public void viewAllCountries(){
        btnViewAllCountries.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage("Countries", new Functions().stringifyAllCountries(dbModel.getAllCountryData()));
                    }
                }
        );
    }*/

    /*Person*/
    /*public PersonTO fillPerson(){
        PersonTO person = new PersonTO();
        person.setName("Mark");
        person.setSurName("MarkCraft");
        person.setUniqueID("6892");
        person.setUserName("mfuentes");
        person.setPassword("des2tramp2dos2");
        person.setEmail("markfuentes2991@gmail.com");
        person.setCountry_alphaID(1);
        person.setBirthDate("21/10/1992");
        person.setCreateDate("24/05/2018");
        person.setImgUrl("person/img/profile01.png");
        person.setUpdateDate("24/05/2018");
        return person;
    }

    public void addPerson(){
        btnAddPerson.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = dbModel.insertPerson(fillPerson());
                        if(isInserted){
                            Toast.makeText(MainActivity.this, "Data inserted correctly", Toast.LENGTH_LONG).show();
                        }else
                        {
                            Toast.makeText(MainActivity.this, "Data Not inserted correctly", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void viewPersons(){
        btnViewPersons.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    showMessage("Person", new Functions().stringifyPerson(dbModel.getPerson()));
                    }
                }
        );
    }

    public void viewAllPersons(){
        btnVieAllPersons.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage("Persons", new Functions().stringifyAllPersons(dbModel.getAllPersonData()));
                    }
                }
        );
    }
*/
}
