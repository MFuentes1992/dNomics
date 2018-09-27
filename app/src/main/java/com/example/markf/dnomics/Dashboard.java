package com.example.markf.dnomics;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.util.Log;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dashboard extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

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

    TextView lblUserName;

    CircleImageView imageView;

    Spinner doptions;

    FloatingActionButton fabNewReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Intent intent = getIntent();

        lblDraft = (TextView)findViewById(R.id.lblDraft);
        lblSubmitted = (TextView)findViewById(R.id.lblSubmitted);
        lblTicket = (TextView)findViewById(R.id.lblTicket);
        lblSetings = (TextView)findViewById(R.id.lblSetings);
        lblUserName = (TextView)findViewById(R.id.lblUserName);
        imageView = (CircleImageView)findViewById(R.id.profile_image);
        doptions = (Spinner)findViewById(R.id.dashboardOptions);
        fabNewReport = findViewById(R.id.fabNewReport);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dashboard_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        doptions.setAdapter(adapter);
        doptions.setOnItemSelectedListener(this);

        lblDraft.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblSubmitted.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblTicket.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblSetings.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));

        usuario = intent.getStringExtra("_usuario");
        password = intent.getStringExtra("_password");
        nombre = intent.getStringExtra("_nombre");
        surname = intent.getStringExtra("_surname");
        uniqueID = intent.getStringExtra("_uniqueid");
        email = intent.getStringExtra("_email");
        dbModel =  new DatabaseModel(this);

        lblUserName.setText(nombre);

        editProfile();
        newReport();
    }

    private void goToEditProfile(){
        Intent intent = new Intent(Dashboard.this, EditProfile.class);
        intent.putExtra("_usuario", usuario);
        intent.putExtra("_password", password);
        intent.putExtra("_nombre", nombre);
        intent.putExtra("_surname", surname);
        intent.putExtra("_uniqueid", uniqueID);
        Dashboard.this.startActivity(intent);
    }

    private void goToNewReport(){
        Intent intent = new Intent(Dashboard.this, NewReport.class);
        intent.putExtra("_uniqueid", uniqueID);
        Dashboard.this.startActivity(intent);
    }

    public void editProfile(){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEditProfile();
            }
        });

        lblSetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEditProfile();
            }
        });
    }

    public void newReport(){
        fabNewReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewReport();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //Do not allow go back when dashboard -- User must close session
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        Log.d("Pos", ""+pos);
        if(pos == 1)
            goToEditProfile();
        else if(pos == 2){
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setTitle("Sing Out")
                    .setMessage("Are you sure you want to sing out?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            salir();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                            doptions.setSelection(0);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

    }

    public void onNothingSelected(AdapterView<?> parent){

    }

    public void salir(){
        dbModel.close();

        alphaCountry = "";
        country = "";
        usuario = "";
        password = "";
        nombre = "";
        surname = "";
        uniqueID = "";
        email = "";

        Intent intent = new Intent(Dashboard.this, MainActivity.class);
        Dashboard.this.startActivity(intent);
    }
}
