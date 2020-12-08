package com.systemesmobiles.tp5_part6;

public class GeoIP {

    String status;
    String query;
    String country;
    String countryCode;


    @Override
    public String toString() {
        return
                "status='" + status + '\'' +
                ", query='" + query + '\'' +
                ", country='" + country + '\'' +
                ", countryCode='" + countryCode + '\''
                ;
    }
}
