package com.example.markf.dnomics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    Button btnSave;
    long lastID = 0;
    int personID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_report);

        Intent intent = getIntent();
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

    private String getReportDate(){
        int day = reportDate.getDayOfMonth();
        int month = reportDate.getMonth();
        int year = reportDate.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
        String date = df.format(calendar.getTime());
        return date;
    }

    private void goToLineItem(long reportID){
        Intent intent = new Intent(NewReport.this, LineItem.class);
        intent.putExtra("reportID", String.valueOf(reportID));
        NewReport.this.startActivity(intent);
    }
}
