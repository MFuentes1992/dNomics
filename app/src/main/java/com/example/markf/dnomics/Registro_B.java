package com.example.markf.dnomics;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Registro_B extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView titleActivity;
    TextView lblbirthDate;
    TextView lblCountry;

    Button btnSaveRegistro;
    DatePicker sBirthDate;

    Spinner spinnerRegistroCountry;

    String alphaCountry;
    String country;
    String usuario;
    String password;
    String nombre;
    String surname;
    String uniqueID;
    String email;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__b);
        Intent intent = getIntent();
        usuario = intent.getStringExtra("_usuario");
        password = intent.getStringExtra("_password");
        nombre = intent.getStringExtra("_nombre");
        surname = intent.getStringExtra("_surname");
        uniqueID = intent.getStringExtra("_uniqueid");
        email = intent.getStringExtra("_email");
        
        lblbirthDate = (TextView)findViewById(R.id.lblBirthDate);
        lblCountry = (TextView)findViewById(R.id.lblCountry);
        btnSaveRegistro = (Button)findViewById(R.id.btnSaveRegistro);
        sBirthDate = (DatePicker)findViewById(R.id.birthDate);
        progressBar = (ProgressBar)findViewById(R.id.indeterminateBar);

        spinnerRegistroCountry = (Spinner)findViewById(R.id.spinnerRegistroCountry);

        btnSaveRegistro.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblbirthDate.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblCountry.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.registro_country, R.layout.support_simple_spinner_dropdown_item);
        spinnerRegistroCountry.setAdapter(adapter);
        spinnerRegistroCountry.setOnItemSelectedListener(Registro_B.this);
        saveData();
    }

    public String getBirthDate(){
        int day = sBirthDate.getDayOfMonth();
        int month = sBirthDate.getMonth() + 1;
        int year = sBirthDate.getYear();
        String date = ""+day+"-"+month+"-"+year;
        return date;
    }

    public String getCurrentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        country = spinnerRegistroCountry.getSelectedItem().toString();
        alphaCountry = "CO"+spinnerRegistroCountry.getSelectedItemPosition();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        country = spinnerRegistroCountry.getSelectedItem().toString();
        alphaCountry = "CO"+spinnerRegistroCountry.getSelectedItemPosition();
    }

    public void goToDashboard(){
        Intent intent = new Intent(Registro_B.this, Dashboard.class);
        intent.putExtra("_usuario",usuario);
        intent.putExtra("_password",password);
        intent.putExtra("_nombre",nombre);
        intent.putExtra("_surname",surname);
        intent.putExtra("_uniqueid",uniqueID);
        intent.putExtra("_email",email);
        Registro_B.this.startActivity(intent);
    }

    public void doInBackgroud(){
        new DaemonHandler(this, new DaemonHandler.AsyncResponse(){
            @Override
            public void processFinish(){
                goToDashboard();
            }
        }).execute(alphaCountry,country,nombre,surname,uniqueID,usuario,password,email,getBirthDate(),getCurrentDate(),getCurrentDate());
    }

    public void saveData(){
        btnSaveRegistro.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressBar.setVisibility(View.VISIBLE);
                        doInBackgroud();
                    }
                }
        );
    }
}
