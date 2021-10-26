package com.example.team_2_tdp_mt;



import static java.time.LocalTime.*;

import android.widget.TextClock;

import com.google.firebase.database.ServerValue;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class DataScreen {
    private String busNumber;
    private String passengers;
    private String offBoard;
    private String goingTo;
    private String comingFrom;
    private String currentTime;



    public DataScreen(String bNumber, String passengers, String offBoard,String goingTo) {
        this.busNumber = bNumber;
        this.passengers = passengers;
        this.offBoard = offBoard;
        this.goingTo = goingTo;

    }

    public String getGoingTo() {
        return goingTo;
    }

    public void setGoingTo(String goingTo) {
        this.goingTo = goingTo;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public String getOffBoard() {
        return offBoard;
    }

    public void setOffBoard(String offBoard) {
        this.offBoard = offBoard;
    }


}
