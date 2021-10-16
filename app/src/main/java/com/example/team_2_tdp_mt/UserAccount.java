package com.example.team_2_tdp_mt;

/**
 * A class for user accounts info model
 */
public class UserAccount {
    private String mEmail;
    private String mPwd;
    private String mFName;
    private String mLName;
    private String mStaffID;        //Firebase Uid

    public UserAccount() { }  //Empty Conductor for Firebase realtime database

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPwd() {
        return mPwd;
    }

    public void setmPwd(String mPwd) {
        this.mPwd = mPwd;
    }

    public String getmFName() {
        return mFName;
    }

    public void setmFName(String mFName) {
        this.mFName = mFName;
    }

    public String getmLName() {
        return mLName;
    }

    public void setmLName(String mLName) {
        this.mLName = mLName;
    }

    public String getmStaffID() {
        return mStaffID;
    }

    public void setmStaffID(String mStaffID) {
        this.mStaffID = mStaffID;
    }
}
