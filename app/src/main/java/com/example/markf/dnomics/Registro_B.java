package com.example.markf.dnomics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Registro_B extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DatabaseModel dbModel;
    TextView titleActivity;
    TextView lblbirthDate;
    TextView lblCountry;

    Button btnSaveRegistro;
    DatePicker sBirthDate;

    Spinner spinnerRegistroCountry;

    String birthDate;
    String country;
    String usuario;
    String password;
    String nombre;
    String surname;
    String uniqueID;
    String email;

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
        dbModel =  new DatabaseModel(this);

        titleActivity = (TextView)findViewById(R.id.activityTitleRegistroB);
        lblbirthDate = (TextView)findViewById(R.id.lblBirthDate);
        lblCountry = (TextView)findViewById(R.id.lblCountry);
        btnSaveRegistro = (Button)findViewById(R.id.btnSaveRegistro);
        sBirthDate = (DatePicker)findViewById(R.id.birthDate);

        spinnerRegistroCountry = (Spinner)findViewById(R.id.spinnerRegistroCountry);

        btnSaveRegistro.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblbirthDate.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblCountry.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        titleActivity.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.RIGHTEOUS));

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.registro_country, R.layout.support_simple_spinner_dropdown_item);
        spinnerRegistroCountry.setAdapter(adapter);
        spinnerRegistroCountry.setOnItemSelectedListener(Registro_B.this);

        saveData();
    }

    public String getBirthDate(){
        int day = sBirthDate.getDayOfMonth();
        int month = sBirthDate.getMonth();
        int year = sBirthDate.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
        String date = df.format(calendar.getTime());
        return date;
    }

    public String getCurrentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void saveData(){
        btnSaveRegistro.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Functions helper = new Functions();
                        boolean isInserted = dbModel.insertPerson( helper.fillPerson(nombre, surname, uniqueID, usuario, password, email, country, getBirthDate(), getCurrentDate(), getCurrentDate(), "img/profile/profile.png"));
                        if(isInserted){
                            Toast.makeText(Registro_B.this, "Data inserted correctly", Toast.LENGTH_LONG).show();
                        }else
                        {
                            Toast.makeText(Registro_B.this, "Data Not inserted correctly", Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );
    }
}
