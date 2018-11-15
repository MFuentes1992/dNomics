package com.example.markf.dnomics;

/**
 * Created by markf on 14/11/2018.
 */

public class ReportTO {

    private int reportID;
    private int personID;
    private String ReportName;
    private String ReportDate;
    private String ReportLocation;

    public ReportTO(){
        reportID = 0;
        ReportName = "";
        ReportDate = "";
        ReportLocation = "";
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public int getReportID() {
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

    public void setReportID()
    {

    }

}
