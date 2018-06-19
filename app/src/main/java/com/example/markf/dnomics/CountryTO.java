package com.example.markf.dnomics;

/**
 * Created by markf on 24/05/2018.
 */

public class CountryTO {

    private int countryID;
    private String countryAlpha;
    private String name;
    private String description;
    private double longitud;
    private double latituded;

    public CountryTO(){
        countryID = 0;
        countryAlpha = "";
        name = "";
        description = "";
        longitud = 0.0F;
        latituded = 0.0F;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getCountryAlpha() {
        return countryAlpha;
    }

    public void setCountryAlpha(String countryAlpha) {
        this.countryAlpha = countryAlpha;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatituded() {
        return latituded;
    }

    public void setLatituded(double latituded) {
        this.latituded = latituded;
    }
}
