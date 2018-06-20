package com.example.markf.dnomics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Registro_B extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView titleActivity;
    TextView lblbirthDate;
    TextView lblCountry;

    Button btnNextRegistroB;

    Spinner spinnerRegistroCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__b);
        Intent intent = getIntent();
        String value = intent.getStringExtra("key");

        titleActivity = (TextView)findViewById(R.id.activityTitleRegistroB);
        lblbirthDate = (TextView)findViewById(R.id.lblBirthDate);
        lblCountry = (TextView)findViewById(R.id.lblCountry);
        btnNextRegistroB = (Button)findViewById(R.id.btnNextRegistroB);

        spinnerRegistroCountry = (Spinner)findViewById(R.id.spinnerRegistroCountry);

        btnNextRegistroB.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblbirthDate.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblCountry.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        titleActivity.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.RIGHTEOUS));

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.registro_country, R.layout.support_simple_spinner_dropdown_item);
        spinnerRegistroCountry.setAdapter(adapter);
        spinnerRegistroCountry.setOnItemSelectedListener(Registro_B.this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
