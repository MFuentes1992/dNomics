package com.example.markf.dnomics;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Registro_A extends AppCompatActivity {
    TextView lblPassword;
    TextView lblEmail;
    EditText txtPasswordRegistro;
    EditText txtEmailRegistro;

    Button btnNextTRegistro;
    String password;
    String nombre;
    String email;

    //Variables para el REST service
    String jsonResponse = "";
    String url = "http://192.168.0.14/add_person.php";
    HashMap<String, String> params = new HashMap<>();
    RequestHandler requester = new RequestHandler();

    LinearLayout registroAContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_a);
        Intent intent = getIntent();
        String value = intent.getStringExtra("key");
        lblPassword = (TextView)findViewById(R.id.lblPasswordRegistro);
        lblEmail = (TextView)findViewById(R.id.lblEmail);
        btnNextTRegistro = (Button) findViewById(R.id.btnNextRegistro);
        txtPasswordRegistro = (EditText) findViewById(R.id.txtPasswordRegistro);
        txtEmailRegistro = (EditText) findViewById(R.id.txtEmailRegistro);
        lblPassword.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblEmail.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        btnNextTRegistro.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));

        btnNext();
    }

    public String getCurrentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

    private void savePersonIntoDB(){

        new HellCat(getApplicationContext(), new HellCat.AsyncTask() {
            @Override
            public void finishHellCat() {

                try {

                    JSONObject json = new JSONObject(jsonResponse);
                    if(json.getBoolean("success")){
                        //goToDashboard();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("Message", jsonResponse);
            }

            @Override
            public void workHellCat() {

                //Storing a fake image into the image data field in the person's record - all users must have an initial value
                Bitmap fakeImg = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.alisa);
                //Call ImageHandler
                ImageHandler imageMgr = new ImageHandler();
                //Convert bitmap into byte array
                imageMgr.setImageDataFromBitmap(fakeImg);

                String image = Base64.encodeToString(imageMgr.getImageData(),
                        Base64.DEFAULT);
                getInfo();
                params.put("source", "Android:App");
                params.put("user_password", password);
                params.put("email", email);
                params.put("img_name", "profile");
                params.put("create_date", getCurrentDate());
                params.put("update_date", getCurrentDate());
                params.put("img_data", image);
                params.put("currencyID", String.valueOf(2));
                params.put("statusID", "1");

                jsonResponse = requester.sendPostRequest(url, params);
            }
        }).execute();

    }

    public void btnNext(){
        btnNextTRegistro.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        savePersonIntoDB();

                    }
                }
        );
    }


    public void getInfo(){
        password = txtPasswordRegistro.getText().toString();
        email = txtEmailRegistro.getText().toString();
    }
}
