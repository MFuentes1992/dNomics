package com.example.markf.dnomics;

/**
 * Created by markf on 09/02/2019.
 */

public class LineItemTO {

    private long lineItemID;
    private long reportID;
    private String lineItemDate;
    private double lineItemTotal;
    private String lineItemPurpose;
    private String lineItemMerchant;
    private String lineItemAllocation;


    public LineItemTO(){
        lineItemID = 0;
        reportID = 0;
        lineItemDate = "";
        lineItemTotal = 0.0;
        lineItemPurpose = "";
        lineItemAllocation = "";
    }

    public long getLineItemID() {
        return lineItemID;
    }

    public void setLineItemID(long lineItemID) {
        this.lineItemID = lineItemID;
    }

    public long getReportID() {
        return reportID;
    }

    public void setReportID(long reportID) {
        this.reportID = reportID;
    }

    public String getLineItemDate() {
        return lineItemDate;
    }

    public void setLineItemDate(String lineItemDate) {
        this.lineItemDate = lineItemDate;
    }

    public double getLineItemTotal() {
        return lineItemTotal;
    }

    public void setLineItemTotal(double lineItemTotal) {
        this.lineItemTotal = lineItemTotal;
    }

    public String getLineItemPurpose() {
        return lineItemPurpose;
    }

    public void setLineItemPurpose(String lineItemPurpose) {
        this.lineItemPurpose = lineItemPurpose;
    }


    public String getLineItemAllocation() {
        return lineItemAllocation;
    }

    public void setLineItemAllocation(String lineItemAllocation) {
        this.lineItemAllocation = lineItemAllocation;
    }

    public String getLineItemMerchant() {
        return lineItemMerchant;
    }

    public void setLineItemMerchant(String lineItemMerchant) {
        this.lineItemMerchant = lineItemMerchant;
    }
}
