package com.example.markf.dnomics;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewReport extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseModel dbModel;
    TextView lblReportName;
    TextView lblNewReport;
    TextView lblDate;
    TextView lblLocation;
    DatePicker reportDate;
    EditText txtReportName;
    EditText txtReportLocation;

    PersonTO activeSession = null;
    String usuario;
    String password;

    Button btnSave;
    long lastID = 0;
    int personID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_report);

        Intent intent = getIntent();
        usuario = intent.getStringExtra("_usuario");
        password = intent.getStringExtra("_password");
        personID = Integer.parseInt(intent.getStringExtra("personID"));
        dbModel = new DatabaseModel(getApplicationContext());

        lblReportName = (TextView)findViewById(R.id.lblReportName);
        lblDate = (TextView)findViewById(R.id.lblReportDate);
        lblLocation = (TextView)findViewById(R.id.lblLocation);
        btnSave = (Button)findViewById(R.id.btnGoToLineItem);
        reportDate = (DatePicker)findViewById(R.id.ReportDate);

        txtReportName = (EditText)findViewById(R.id.txtReportName);
        txtReportLocation = (EditText)findViewById(R.id.txtReportLocation);

        lblReportName.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblDate.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblLocation.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        btnSave.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));

        Init();
        saveReport();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //TODO
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //TODO
    }

    private void saveReport(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReportIntoDB();
            }
        });
    }

    private void saveReportIntoDB(){

        new HellCat(getApplicationContext(), new HellCat.AsyncTask() {
            @Override
            public void finishHellCat() {
                if(lastID > 0){
                    goToLineItem(lastID);
                }
            }

            @Override
            public void workHellCat() {
                lastID = dbModel.insertReport(new Functions().fillReport(txtReportName.getText().toString(), getReportDate(), txtReportLocation.getText().toString(), personID, "RPA0000000", 0.0, 1));
            }
        }).execute();

    }

    public void Init(){
        new HellCat(getApplicationContext(), new HellCat.AsyncTask() {
            @Override
            public void finishHellCat() {
            }
            @Override
            public void workHellCat() {
                if(usuario.length() > 0 && password.length() > 0)
                    activeSession = getUserSession(usuario, password);
            }
        }).execute();
    }

    private String getReportDate(){
        int day = reportDate.getDayOfMonth();
        int month = reportDate.getMonth();
        int year = reportDate.getYear();
        String date = day+"-"+(month+1)+"-"+year;
        return date;
    }

    private void goToLineItem(long reportID){
        Intent intent = new Intent(NewReport.this, LineItem.class);
        intent.putExtra("reportID", String.valueOf(reportID));
        //intent.putExtra("_usuario", activeSession.getUserName());
        //intent.putExtra("_password",activeSession.getPassword());
        intent.putExtra("reportNumber", "");
        NewReport.this.startActivity(intent);
    }

    public PersonTO getUserSession(String userName, String pass){
        PersonTO person = new PersonTO();
        DatabaseModel dbModel = new DatabaseModel(getApplicationContext());
        SQLiteDatabase db = dbModel.getWritableDatabase();
        //person = dbModel.getPersonByUserNamePass(db,userName,pass);
        //Log.d("PersonName:", person.getName());
        return person;
    }
}
