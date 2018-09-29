package com.example.markf.dnomics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LineItem extends AppCompatActivity {

    DatabaseModel dbModel;
    TextView lblLineItemDate;
    TextView lblLineItemAmt;
    TextView lblLineItemDescription;
    TextView lblLineItemFrom;
    TextView lblLineItemTo;
    TextView lblLineItemMerchant;
    TextView lblLineItemAllocation;

    Button btnAttachTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_item);

        lblLineItemDate = (TextView)findViewById(R.id.lblLineItemDate);
        lblLineItemAmt = (TextView)findViewById(R.id.lblLineItemAmt);
        lblLineItemDescription = (TextView)findViewById(R.id.lblLineItemDescription);
        lblLineItemTo = (TextView)findViewById(R.id.lblLineItemTo);
        lblLineItemMerchant = (TextView)findViewById(R.id.lblLineItemMerchant);
        lblLineItemAllocation = (TextView)findViewById(R.id.lblLineItemAllocation);
        lblLineItemFrom = (TextView)findViewById(R.id.lblLineItemFrom);
        btnAttachTicket = (Button)findViewById(R.id.btnAttachTicket);

        lblLineItemDate.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblLineItemAmt.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblLineItemDescription.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblLineItemTo.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblLineItemMerchant.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblLineItemAllocation.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblLineItemFrom.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        btnAttachTicket.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
    }
}
