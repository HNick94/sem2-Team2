package com.example.team_2_tdp_mt;

public class Info {

    private String locationId;

    public Info() { }  //Empty Conductor for Firebase realtime database

    public Info(String locationId){
        this.locationId = locationId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }


}
