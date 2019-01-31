package com.example.markf.dnomics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LineItem extends AppCompatActivity {

    TextView lblLineItemAmt;
    TextView lblLineItemDescription;
    TextView lblLineItemMerchant;
    TextView lblLineItemAllocation;
    TextView lblReportNumber;
    TextView lblReportName;

    Button btnAttachTicket;
    Button btnSaveLineItem;

    ReportTO reportSession = null;

    boolean setReportNumberLabel = false;

    long reportID = 0;
    double reportTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_item);

        Intent intent = getIntent();
        reportID = Long.parseLong(intent.getStringExtra("reportID"));

        lblLineItemAmt = (TextView)findViewById(R.id.lblLineItemAmt);
        lblLineItemDescription = (TextView)findViewById(R.id.lblLineItemDescription);
        lblLineItemMerchant = (TextView)findViewById(R.id.lblLineItemMerchant);
        lblLineItemAllocation = (TextView)findViewById(R.id.lblLineItemAllocation);
        lblReportNumber = (TextView)findViewById(R.id.reportNumber);
        lblReportName = (TextView)findViewById(R.id.LireportName);
        btnAttachTicket = (Button)findViewById(R.id.btnAttachTicket);
        btnSaveLineItem = (Button)findViewById(R.id.btnSaveLineItem);

        lblLineItemAmt.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblLineItemDescription.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblLineItemMerchant.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblLineItemAllocation.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        btnAttachTicket.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        btnSaveLineItem.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));


        Init();
        //reportSession = getReportSession(reportID);
    }

    public void Init(){
        new HellCat(getApplicationContext(), new HellCat.AsyncTask() {
            @Override
            public void finishHellCat() {
                if(setReportNumberLabel)
                lblReportNumber.setText(reportSession.getReportNumber());
                lblReportName.setText(reportSession.getReportName());
            }

            @Override
            public void workHellCat() {
                reportSession = getReportSession(reportID);
                reportSession.setReportNumber(generateReportNumber(reportID));
                DatabaseModel dbModel = new DatabaseModel(getApplicationContext());
                setReportNumberLabel = dbModel.updateReport(reportSession);
                reportSession = getReportSession(reportID);
            }
        }).execute();
    }

    private String generateReportNumber(Long reportID){
        String reportString = "RPA0000000";
        String strReportID = String.valueOf(reportID);
        String reportNumber = reportString.substring(0,reportString.length() - strReportID.length()) + strReportID;
        return reportNumber;
    }

    public ReportTO getReportSession(long reportID){
        ReportTO report = new ReportTO();
        DatabaseModel dbModel = new DatabaseModel(getApplicationContext());
        report = dbModel.getReportByID(reportID);
        return report;
    }
}
