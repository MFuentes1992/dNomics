package com.example.markf.dnomics;

/**
 * Created by markf on 14/11/2018.
 */

public class ReportTO {

    private long reportID;
    private long personID;
    private double reportTotal;
    private String ReportName;
    private String ReportDate;
    private String ReportLocation;
    private String ReportNumber;

    public ReportTO(){
        reportID = 0;
        reportTotal = 0.0;
        ReportName = "";
        ReportDate = "";
        ReportLocation = "";
        ReportNumber = "";
    }

    public long getPersonID() {
        return personID;
    }

    public void setPersonID(long personID) {
        this.personID = personID;
    }

    public long getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public String getReportName() {
        return ReportName;
    }

    public void setReportName(String reportName) {
        ReportName = reportName;
    }

    public String getReportDate() {
        return ReportDate;
    }

    public void setReportDate(String reportDate) {
        ReportDate = reportDate;
    }

    public String getReportLocation() {
        return ReportLocation;
    }

    public void setReportLocation(String reportLocation) {
        ReportLocation = reportLocation;
    }

    public void setReportID(long reportID)
    {
        this.reportID = reportID;
    }

    public String getReportNumber() {
        return ReportNumber;
    }

    public void setReportNumber(String reportNumber) {
        ReportNumber = reportNumber;
    }

    public double getReportTotal() {
        return reportTotal;
    }

    public void setReportTotal(double reportTotal) {
        this.reportTotal = reportTotal;
    }
}
