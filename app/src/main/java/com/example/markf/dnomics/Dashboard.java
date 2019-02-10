package com.example.markf.dnomics;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dashboard extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    // Objects and variables for getting user's db info
    Intent intent = null;
    //DatabaseModel dbModel;
    PersonTO activeSession = null;
    //Strings
    String alphaCountry;
    String country;
    String usuario;
    String password;
    String nombre;
    String surname;
    String uniqueID;
    String email;
    //TextViews
    TextView lblDraft;
    TextView lblSubmitted;
    TextView lblTicket;
    TextView lblSetings;
    TextView lblUserName;
    TextView lblDraftTotal;
    //Circular Profile Image
    CircleImageView profileImage;
    //DropDown Menu options
    Spinner doptions;
    //New Expense Report
    FloatingActionButton fabNewReport;
    int totalDraftReports = 0;
    int personID = 0;

    ArrayList<ReportTO> totalDraft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        intent = getIntent();
        lblDraft = (TextView)findViewById(R.id.lblDraft);
        lblSubmitted = (TextView)findViewById(R.id.lblSubmitted);
        lblTicket = (TextView)findViewById(R.id.lblTicket);
        //lblSetings = (TextView)findViewById(R.id.lblSetings);
        lblUserName = (TextView)findViewById(R.id.lblUserName);
        lblDraftTotal = (TextView)findViewById(R.id.lblDraftTotal);
        profileImage = (CircleImageView)findViewById(R.id.profile_image);
        doptions = (Spinner)findViewById(R.id.dashboardOptions);
        fabNewReport = findViewById(R.id.fabNewReport);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dashboard_options, R.layout.support_simple_spinner_dropdown_item);
        doptions.setAdapter(adapter);
        doptions.setOnItemSelectedListener(this);

        lblDraft.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblSubmitted.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblTicket.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        //lblSetings.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));

        Init();
        editProfile();
        newReport();
        goToDraft();
    }

    private void goToEditProfile(){
        Intent intent = new Intent(Dashboard.this, EditProfile.class);
        intent.putExtra("_usuario", usuario);
        intent.putExtra("_password", password);
        intent.putExtra("_nombre", nombre);
        intent.putExtra("_surname", surname);
        intent.putExtra("_uniqueid", uniqueID);
        intent.putExtra("_email",email);
        Dashboard.this.startActivity(intent);
    }

    private void goToNewReport(){
        Intent intent = new Intent(Dashboard.this, NewReport.class);
        intent.putExtra("_uniqueid", activeSession.getUniqueID());
        intent.putExtra("_usuario", activeSession.getUserName());
        intent.putExtra("_password", activeSession.getPassword());
        intent.putExtra("personID", String.valueOf(activeSession.getPersonID()));
        Dashboard.this.startActivity(intent);
    }

    public void editProfile(){
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEditProfile();
            }
        });

        /*lblSetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEditProfile();
            }
        });*/
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


    private void Init(){

        //Get user's info through the activity intent, when came from new entry

        usuario = intent.getStringExtra("_usuario");
        password = intent.getStringExtra("_password");
        nombre = intent.getStringExtra("_nombre");
        surname = intent.getStringExtra("_surname");
        uniqueID = intent.getStringExtra("_uniqueid");
        email = intent.getStringExtra("_email");
        personID = Integer.parseInt(intent.getStringExtra("_personID"));

        //Start Daemon ---HELLCAT--- To retrieve user information in another thread
        new HellCat( getApplicationContext(), new HellCat.AsyncTask(){
            @Override
            public void workHellCat(){
                activeSession = getUserSession(usuario, password);
                totalDraft = getDraftReports(personID);
                //Log.d("Draft:",String.valueOf(totalDraftReports));

            }
            @Override
            public  void finishHellCat(){
                Log.d("HellCat", "Finishing Thread...");
                if(activeSession.getName().length() <= 0){
                    salir();
                }
                ImageHandler bitmapImage = new ImageHandler(activeSession.getImgData());
                profileImage.setImageBitmap(bitmapImage.getImageDataInBitmap());
                lblUserName.setText(activeSession.getName()+" "+activeSession.getSurName().substring(0,1)+".");
                totalDraftReports = totalDraft.size();
                lblDraftTotal.setText(String.valueOf(totalDraftReports));
            }
        }).execute();
    }

    public PersonTO getUserSession(String userName, String pass){
        PersonTO person = new PersonTO();
        DatabaseModel dbModel = new DatabaseModel(getApplicationContext());
        SQLiteDatabase db = dbModel.getWritableDatabase();
        person = dbModel.getPersonByUserNamePass(db,userName,pass);
        Log.d("PersonName:", person.getName());
        return person;
    }

    public void salir(){
        DatabaseModel dbModel = new DatabaseModel(getApplicationContext());
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

    public ArrayList<ReportTO> getDraftReports(int personID){
        DatabaseModel dbModel = new DatabaseModel(getApplicationContext());
        SQLiteDatabase db = dbModel.getWritableDatabase();
        ArrayList<ReportTO> reports = dbModel.getAllDraftReports(personID);
        return  reports;
    }

    private void goToDraft(){
        lblDraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Draft.class);
                //Intent intent = new Intent(MainActivity.this, Dashboard.class);
                intent.putExtra("personID", String.valueOf(activeSession.getPersonID()));
                Dashboard.this.startActivity(intent);
            }
        });
    }
}
