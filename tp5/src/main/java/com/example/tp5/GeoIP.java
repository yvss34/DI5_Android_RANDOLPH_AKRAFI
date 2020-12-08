package com.example.tp5;

public class GeoIP {

    String status;
    String query;
    String country;
    String countryCode;


    @Override
    public String toString() {
        return "GeoIP{" +
                "status='" + status + '\'' +
                ", query='" + query + '\'' +
                ", country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
