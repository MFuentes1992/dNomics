package com.example.markf.dnomics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    DatabaseModel dbModel;

    String alphaCountry;
    String country;
    String usuario;
    String password;
    String nombre;
    String surname;
    String uniqueID;
    String email;

    TextView lblDraft;
    TextView lblSubmitted;
    TextView lblTicket;
    TextView lblSetings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Intent intent = getIntent();

        lblDraft = (TextView)findViewById(R.id.lblDraft);
        lblSubmitted = (TextView)findViewById(R.id.lblSubmitted);
        lblTicket = (TextView)findViewById(R.id.lblTicket);
        lblSetings = (TextView)findViewById(R.id.lblSetings);

        lblDraft.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblSubmitted.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblTicket.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblSetings.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        /*usuario = intent.getStringExtra("_usuario");
        password = intent.getStringExtra("_password");
        nombre = intent.getStringExtra("_nombre");
        surname = intent.getStringExtra("_surname");
        uniqueID = intent.getStringExtra("_uniqueid");
        email = intent.getStringExtra("_email");
        dbModel =  new DatabaseModel(this);*/
    }
}
