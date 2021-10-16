package com.example.team_2_tdp_mt;



import static java.time.LocalTime.*;

import android.widget.TextClock;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class DataScreen {
    private String busNumber;
    private String passengers;
    private String offBoard;
    private String currentTime;


    public DataScreen(String bNumber, String passengers, String offBoard) {
        this.busNumber = bNumber;
        this.passengers = passengers;
        this.offBoard = offBoard;

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
