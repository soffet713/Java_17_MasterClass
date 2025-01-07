package com.genericschallenge;

public class NationalMonument extends Point {

    private String country;
    private String yearEstablished;

    public NationalMonument(String name, String type, String location,
                            String country, String yearEstablished) {
        super(name, type, location);
        this.country = country;
        this.yearEstablished = yearEstablished;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getYearEstablished() {
        return yearEstablished;
    }

    public void setYearEstablished(String yearEstablished) {
        this.yearEstablished = yearEstablished;
    }

    @Override
    public String toString() {
        return super.name() + " | Monument Type: " + super.type() + " | Location: " + getCountry() + " | Established: " +
                getYearEstablished();
    }
}
