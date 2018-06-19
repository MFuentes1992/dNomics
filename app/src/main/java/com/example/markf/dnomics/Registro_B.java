package com.example.markf.dnomics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Registro_B extends AppCompatActivity {

    TextView titleActivity;
    TextView lblbirthDate;

    Button btnNextRegistroB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__b);
        Intent intent = getIntent();
        String value = intent.getStringExtra("key");

        titleActivity = (TextView)findViewById(R.id.activityTitleRegistroB);
        lblbirthDate = (TextView)findViewById(R.id.lblBirthDate);
        btnNextRegistroB = (Button)findViewById(R.id.btnNextRegistroB);

        btnNextRegistroB.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblbirthDate.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        titleActivity.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.RIGHTEOUS));
    }
}
