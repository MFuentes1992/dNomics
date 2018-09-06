package com.example.markf.dnomics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class EditProfile extends AppCompatActivity {

    TextView lblUserEditProfile;
    TextView lblPasswordEditProfile;
    TextView lblNombreEditProfile;
    TextView lblSurnameEditProfile;
    TextView lblUniqueIDEditProfile;

    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        lblUserEditProfile = (TextView)findViewById(R.id.lblUserEditProfile);
        lblPasswordEditProfile =(TextView)findViewById(R.id.lblPasswordEditProfile);
        lblNombreEditProfile = (TextView)findViewById(R.id.lblNombreEditProfile);
        lblSurnameEditProfile = (TextView)findViewById(R.id.lblSurnameEditProfile);
        lblUniqueIDEditProfile = (TextView)findViewById(R.id.lblUniqueIDEditProfile);

        btnSave = (Button)findViewById(R.id.btnUpdateEditProfile);

        lblUserEditProfile.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblPasswordEditProfile.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblNombreEditProfile.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblSurnameEditProfile.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblUniqueIDEditProfile.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        btnSave.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
    }
}
