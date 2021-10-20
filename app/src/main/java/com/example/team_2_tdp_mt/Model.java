package com.example.team_2_tdp_mt;

import java.sql.Timestamp;
import java.util.Date;

public class Model {
    String busNumber;
    String passengers;
    String goingTo;
    long timestamp;

    public Model(){

    }

    public Model(String busNumber, String passengers, String goingTo,long myTimeStamp) {
        this.busNumber = busNumber;
        this.passengers = passengers;
        this.goingTo = goingTo;
        this.timestamp = myTimeStamp;

    }

    public String getGoingTo() {

        return goingTo;
    }

    public void setGoingTo(String goingTo) {

        this.goingTo = goingTo;
    }

    public String getbusNumber() {

        return busNumber;
    }

    public void setBus_no(String busNumber) {

        this.busNumber = busNumber;
    }

    public String getPassengers() {

        return passengers;
    }

    public void setPassengers(String passengers) {

        this.passengers = passengers;
    }

    public long getTimestamp() {

        return timestamp;
    }

    public void setMyTimeStamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
