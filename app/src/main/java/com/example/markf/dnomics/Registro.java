package com.example.markf.dnomics;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Registro extends AppCompatActivity {

    TextView lblUsuario;
    TextView lblPassword;
    TextView lblNombre;
    TextView lblSurname;
    TextView lbluniqueID;
    TextView lblEmail;
    TextView titleActivity;

    EditText txtUsuarioRegistro;
    EditText txtPasswordRegistro;
    EditText txtNombreRegistro;
    EditText txtApellidoRegistro;
    EditText txtUniqueRegistro;
    EditText txtEmailRegistro;

    Button btnNextTRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Intent intent = getIntent();
        String value = intent.getStringExtra("key");

        lblUsuario = (TextView)findViewById(R.id.lblUserRegistro);
        lblPassword = (TextView)findViewById(R.id.lblPasswordRegistro);
        lblNombre = (TextView)findViewById(R.id.lblNombre);
        lblSurname = (TextView)findViewById(R.id.lblSurname);
        lbluniqueID = (TextView)findViewById(R.id.lblUniqueID);
        lblEmail = (TextView)findViewById(R.id.lblEmail);
        titleActivity = (TextView)findViewById(R.id.activityTitle);
        btnNextTRegistro = (Button) findViewById(R.id.btnNextRegistro);

        txtUsuarioRegistro = (EditText) findViewById(R.id.txtUsuarioRegistro);
        txtPasswordRegistro = (EditText) findViewById(R.id.txtPasswordRegistro);
        txtNombreRegistro = (EditText) findViewById(R.id.txtNombreRegistro);
        txtApellidoRegistro = (EditText) findViewById(R.id.txtApellidoRegistro);
        txtUniqueRegistro = (EditText) findViewById(R.id.txtUniqueRegistro);
        txtEmailRegistro = (EditText) findViewById(R.id.txtEmailRegistro);

        lblPassword.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblNombre.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblSurname.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lbluniqueID.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblEmail.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        titleActivity.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.RIGHTEOUS));
        lblUsuario.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        btnNextTRegistro.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));

        btnNext();
    }

    public void btnNext(){
        btnNextTRegistro.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*Functions functionHelper = new Functions();
                        if(functionHelper.textfieldValidator(txtUsuarioRegistro)){
                            functionHelper.showMessage(v, getString(R.string.showMessageAtention), functionHelper.emptyFieldMsg(v));
                        }*/
                        Intent intent = new Intent(Registro.this, Registro_B.class);
                        intent.putExtra("key","");
                        Registro.this.startActivity(intent);
                    }
                }
        );
    }

}
