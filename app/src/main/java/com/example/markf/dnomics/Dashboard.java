package com.example.markf.dnomics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

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

    TextView lblUserName;

    CircleImageView imageView;
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

    @Override
    public void onBackPressed() {
        //Do not allow go back when dashboard -- User must close session
    }

}
