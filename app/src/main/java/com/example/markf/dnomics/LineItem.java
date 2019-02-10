package com.example.markf.dnomics;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LineItem extends AppCompatActivity {

    TextView lblLineItemAmt;
    TextView lblLineItemDescription;
    TextView lblLineItemMerchant;
    TextView lblLineItemAllocation;
    TextView lblReportNumber;
    TextView lblReportName;

    EditText txtLineItemAmt;
    EditText txtLineItemDescription;
    EditText txtLineItemMerchant;
    EditText txtLineItemAllocation;


    Button btnAttachTicket;
    Button btnSaveLineItem;

    ReportTO reportSession = null;
    PersonTO activeSession = null;

    String usuario;
    String password;

    boolean setReportNumberLabel = false;

    DatabaseModel dModel;

    long reportID = 0;
    long lastID = 0;
    String reportNumber = "";
    double reportTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_item);

        Intent intent = getIntent();
        reportID = Long.parseLong(intent.getStringExtra("reportID"));
        reportNumber = intent.getStringExtra("reportNumber");
        usuario = intent.getStringExtra("_usuario");
        password = intent.getStringExtra("_password");

        dModel = new DatabaseModel(getApplicationContext());

        lblLineItemAmt = (TextView)findViewById(R.id.lblLineItemAmt);
        lblLineItemDescription = (TextView)findViewById(R.id.lblLineItemDescription);
        lblLineItemMerchant = (TextView)findViewById(R.id.lblLineItemMerchant);
        lblLineItemAllocation = (TextView)findViewById(R.id.lblLineItemAllocation);
        lblReportNumber = (TextView)findViewById(R.id.reportNumber);
        lblReportName = (TextView)findViewById(R.id.LireportName);
        btnAttachTicket = (Button)findViewById(R.id.btnAttachTicket);
        btnSaveLineItem = (Button)findViewById(R.id.btnSaveLineItem);

        txtLineItemAmt = (EditText)findViewById(R.id.txtLineItemAmt);
        txtLineItemDescription = (EditText)findViewById(R.id.txtLineItemDescription);
        txtLineItemMerchant = (EditText)findViewById(R.id.txtLineItemMerchant);
        txtLineItemAllocation = (EditText)findViewById(R.id.txtLineItemAllocation);

        lblLineItemAmt.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblLineItemDescription.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblLineItemMerchant.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblLineItemAllocation.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        btnAttachTicket.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        btnSaveLineItem.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));



        Init();
        saveLineItem();
    }

    private void saveLineItem(){
        btnSaveLineItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLineItemIntoDB();
            }
        });
    }

    public void Init(){
        new HellCat(getApplicationContext(), new HellCat.AsyncTask() {
            @Override
            public void finishHellCat() {
                lblReportNumber.setText(reportSession.getReportNumber());
                lblReportName.setText(reportSession.getReportName());
            }
            @Override
            public void workHellCat() {
                if(reportNumber.length() > 0){ /*If report number is not greater than 0 then it is a new line item entry from a new report entry*/
                    reportSession = getReportSession(reportID);
                    reportSession.setReportNumber(generateReportNumber(reportID));
                    DatabaseModel dbModel = new DatabaseModel(getApplicationContext());
                    setReportNumberLabel = dbModel.updateReport(reportSession);
                    reportSession = getReportSession(reportID);
                }else{ /*If the report header already have a value in the reportNumber field then it is not a new line item entry for a new report type*/
                    reportSession = getReportSession(reportID);
                }
                if(usuario.length() > 0  && password.length() > 0){
                    activeSession = getUserSession(usuario, password);
                }
            }
        }).execute();
    }

    @Override
    public void onBackPressed() {
        //Do not allow go back when dashboard -- User must close session
        goToDashboard();
    }

    private void saveLineItemIntoDB(){
        new HellCat(getApplicationContext(), new HellCat.AsyncTask(){

            @Override
            public void finishHellCat() {
                if(lastID > 0){
                    goToDashboard();
                }
            }

            @Override
            public void workHellCat() {
                lastID = dModel.insertLineItem(new Functions().fillLineItem(reportID, reportSession.getReportDate(), Double.parseDouble(txtLineItemAmt.getText().toString()), txtLineItemDescription.getText().toString(), txtLineItemMerchant.getText().toString(),txtLineItemAllocation.getText().toString()));
            }
        }).execute();
    }

    private String generateReportNumber(Long reportID){
        String reportString = "RPA0000000";
        String strReportID = String.valueOf(reportID);
        String reportNumber = reportString.substring(0,reportString.length() - strReportID.length()) + strReportID;
        return reportNumber;
    }

    private ReportTO getReportSession(long reportID){
        ReportTO report = new ReportTO();
        DatabaseModel dbModel = new DatabaseModel(getApplicationContext());
        report = dbModel.getReportByID(reportID);
        return report;
    }

    private void goToDashboard(){
        Intent intent = new Intent(LineItem.this, Dashboard.class);
        intent.putExtra("_usuario", activeSession.getUserName());
        intent.putExtra("_password",activeSession.getPassword());
        intent.putExtra("_personID", String.valueOf(activeSession.getPersonID()));
        LineItem.this.startActivity(intent);
    }

    public PersonTO getUserSession(String userName, String pass){
        PersonTO person = new PersonTO();
        DatabaseModel dbModel = new DatabaseModel(getApplicationContext());
        SQLiteDatabase db = dbModel.getWritableDatabase();
        person = dbModel.getPersonByUserNamePass(db,userName,pass);
        Log.d("PersonName:", person.getName());
        return person;
    }
}
