package com.example.markf.dnomics;

import android.app.ActionBar;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.provider.FontRequest;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    PersonTO activeSession;
    TextView txtTitle;
    TextView iconTitle;
    TextView lblEmail;
    TextView lblPassword;
    TextView lblRegistro;
    TextView lblUserNotFound;
    EditText txtEmail;
    EditText userPassword;
    Button btnLogin;
    DatabaseModel dbModel;

    //Login with Facebook
    LoginButton fbLogin;
    CallbackManager callbackMgr;
    boolean fromFacebook = false;

    String email;
    String password;
    String firstName = "";
    String lastName = "";


    //Variables para el REST service
    String jsonResponse = "";
    URLHandler URL = new URLHandler();
    HashMap<String, String> params;
    RequestHandler requester = new RequestHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbModel = new DatabaseModel(getApplicationContext());

        //Set the font for the title bar
        txtTitle = (TextView)findViewById(R.id.txtTitle);
        iconTitle = (TextView)findViewById(R.id.iconTitle);
        txtEmail = (EditText)findViewById(R.id.txtEmailMain);
        userPassword = (EditText)findViewById(R.id.txtUserPassword);
        iconTitle.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        txtTitle.setTypeface(FontManager.getTypeface(getApplicationContext(),FontManager.RIGHTEOUS));

        //Iconos
        lblEmail = (TextView)findViewById(R.id.lblEmailMain);
        lblPassword = (TextView)findViewById(R.id.lblPassword);
        lblRegistro = (TextView)findViewById(R.id.lblRegristro);
        lblUserNotFound = (TextView)findViewById(R.id.lblUserNotFound);

        lblEmail.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblPassword.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblRegistro.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));


        //Facebook login button variables.
        callbackMgr = CallbackManager.Factory.create();
        fbLogin = (LoginButton)findViewById(R.id.login_button);
        List<String> permissions = new ArrayList<>();
        permissions.add("public_profile");
        permissions.add("email");
        fbLogin.setReadPermissions(permissions);

        FacebookReady();
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

    private void goToDashboard(int personID){
        Intent intent = new Intent(MainActivity.this, Dashboard.class);
        //intent.putExtra("_usuario",userName.getText().toString());
        //intent.putExtra("_password",userPassword.getText().toString());
        //intent.putExtra("_personID", String.valueOf(personID));
        MainActivity.this.startActivity(intent);
    }


    public void userLogin(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txtEmail.getText().toString();
                password =  userPassword.getText().toString();
                if(email.matches("") || password.matches("")){
                    Toast.makeText(MainActivity.this, "Algunos de los campos estan vacios", Toast.LENGTH_LONG).show();
                }else
                {
                    fromFacebook = false;
                    isUserRegistered();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackMgr.onActivityResult(requestCode, resultCode, data);
    }

    private void FacebookReady(){
        fbLogin.registerCallback(callbackMgr, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                /*Log.d("UserID", loginResult.getAccessToken().getUserId());
                Toast.makeText(getApplicationContext(), "Auth Success!", Toast.LENGTH_LONG);*/
                final GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override

                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    email = object.getString("email");
                                    Profile person = Profile.getCurrentProfile();
                                    if(person != null){
                                        firstName = person.getFirstName();
                                        lastName = person.getLastName();
                                        fromFacebook = true;
                                        Log.d("Tag", email);
                                        Log.d("Tag", firstName);
                                    }
                                    isUserRegistered();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "name,email");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "User canceled login", Toast.LENGTH_SHORT);
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT);
            }
        });
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
                    Log.d("Mensaje", json.getString("message"));
                    Toast.makeText(MainActivity.this, json.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
                params = new HashMap<>();
                params.put("source", "Android:App");
                params.put("email", email);
                params.put("first_name", firstName);
                params.put("last_name", lastName);
                params.put("create_date", getCurrentDate());
                params.put("update_date", getCurrentDate());
                params.put("currencyID", String.valueOf(2));
                params.put("statusID", "1");
                if(fromFacebook){
                    params.put("Facebook", "true");
                }

                jsonResponse = requester.sendPostRequest(URL.urlAddUser(), params);
            }
        }).execute();

    }

    private void isUserRegistered(){
        new HellCat(getApplicationContext(), new HellCat.AsyncTask() {
            @Override
            public void finishHellCat() {

                try {

                    JSONObject json = new JSONObject(jsonResponse);
                    if(!json.getBoolean("success") && json.getBoolean("new")){
                        savePersonIntoDB();
                    }else if(json.getBoolean("success") && json.getInt("userID") > 0){
                        PersonTO person = new PersonTO();
                        person.setUserID(json.getInt("userID"));
                        Log.d("userID", String.valueOf(person.getUserID()));
                    } else{
                        lblUserNotFound.setVisibility(View.VISIBLE);
                        txtEmail.setText("");
                        userPassword.setText("");
                        password = null;
                        Log.d("Message","Prueba");
                    }
                    Log.d("Message",json.getString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void workHellCat() {
                params = new HashMap<>();
                params.put("source", "App:Android");
                params.put("email", email);
                if(password != null){
                    params.put("user_password", password);
                }
                jsonResponse = requester.sendPostRequest(URL.urlGetUser(), params);
            }
        }).execute();

    }


}
