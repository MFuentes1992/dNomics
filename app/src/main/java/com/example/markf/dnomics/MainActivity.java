package com.example.markf.dnomics;

import android.app.ActionBar;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.provider.FontRequest;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    PersonTO activeSession;
    TextView txtTitle;
    TextView iconTitle;
    TextView lblUser;
    TextView lblPassword;
    TextView lblRegistro;
    TextView lblUserNotFound;
    EditText userName;
    EditText userPassword;
    Button btnLogin;
    DatabaseModel dbModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbModel = new DatabaseModel(getApplicationContext());

        //Set the font for the title bar
        txtTitle = (TextView)findViewById(R.id.txtTitle);
        iconTitle = (TextView)findViewById(R.id.iconTitle);
        userName = (EditText)findViewById(R.id.txtUserName);
        userPassword = (EditText)findViewById(R.id.txtUserPassword);
        iconTitle.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        txtTitle.setTypeface(FontManager.getTypeface(getApplicationContext(),FontManager.RIGHTEOUS));

        //Iconos
        lblUser = (TextView)findViewById(R.id.lblUser);
        lblPassword = (TextView)findViewById(R.id.lblPassword);
        lblRegistro = (TextView)findViewById(R.id.lblRegristro);
        lblUserNotFound = (TextView)findViewById(R.id.lblUserNotFound);

        lblUser.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblPassword.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblRegistro.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));

        newEntry();
        userLogin();
    }

    public void newEntry(){
        lblRegistro.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Registro_A.class);
                        //Intent intent = new Intent(MainActivity.this, Dashboard.class);
                        intent.putExtra("key","");
                        MainActivity.this.startActivity(intent);
                    }
                }
        );
    }

    public PersonTO getUserSession(String userName, String pass){
        PersonTO person = new PersonTO();
        SQLiteDatabase db = dbModel.getWritableDatabase();
        person = dbModel.getPersonByUserNamePass(db,userName,pass);
        return person;
    }

    private void goToDashboard(int personID){
        Intent intent = new Intent(MainActivity.this, Dashboard.class);
        intent.putExtra("_usuario",userName.getText().toString());
        intent.putExtra("_password",userPassword.getText().toString());
        intent.putExtra("_personID", String.valueOf(personID));
        MainActivity.this.startActivity(intent);
    }

    private void Login(){
        new HellCat(getApplicationContext(), new HellCat.AsyncTask(){
            @Override
            public void workHellCat(){
                activeSession = getUserSession(userName.getText().toString(), userPassword.getText().toString());
            }
            @Override
            public void finishHellCat(){
                if(activeSession.getName().length() > 0){
                    goToDashboard((int)activeSession.getPersonID());
                }else{
                    //Log.d("userNotfound", "User not found");
                    userName.setText("");
                    userPassword.setText("");
                    lblUserNotFound.setVisibility(View.VISIBLE);
                }
            }
        }).execute();
    }

    public void userLogin(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userName.getText().toString().length() <= 0 || userPassword.getText().toString().length() <= 0){
                    Functions functionHelper = new Functions();
                    functionHelper.showMessage(v, getString(R.string.showMessageAtention), functionHelper.emptyFieldMsg(v));
                }else{
                    Login();
                }
            }
        });
    }
}
