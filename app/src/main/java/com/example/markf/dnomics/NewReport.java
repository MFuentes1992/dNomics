package com.example.markf.dnomics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class NewReport extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseModel dbModel;
    TextView lblReportName;
    TextView lblNewReport;
    TextView lblDate;
    TextView lblLocation;
    Button btnSave;
    Spinner location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_report);

        lblReportName = (TextView)findViewById(R.id.lblReportName);
        lblDate = (TextView)findViewById(R.id.lblReportDate);
        lblLocation = (TextView)findViewById(R.id.lblLocation);
        lblNewReport = (TextView)findViewById(R.id.ReportHeaderActivity);
        btnSave = (Button)findViewById(R.id.btnGoToLineItem);
        location = (Spinner)findViewById(R.id.spinnerLocation);

        lblReportName.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblDate.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblLocation.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        //lblNewReport.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        btnSave.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.registro_country, R.layout.support_simple_spinner_dropdown_item);

        location.setAdapter(adapter);
        location.setOnItemSelectedListener(NewReport.this);

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
                goToLineItem();
            }
        });
    }

    private void goToLineItem(){
        Intent intent = new Intent(NewReport.this, LineItem.class);
        NewReport.this.startActivity(intent);
    }
}
