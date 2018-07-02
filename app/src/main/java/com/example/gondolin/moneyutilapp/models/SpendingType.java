package com.example.gondolin.moneyutilapp.models;


public class SpendingType {
    private int spendingID;
    private String spendingType;

    public SpendingType(int spendingID, String spendingType) {
        this.spendingID = spendingID;
        this.spendingType = spendingType;
    }

    public int getSpendingID() {
        return spendingID;
    }

    public void setSpendingID(int spendingID) {
        this.spendingID = spendingID;
    }

    public String getSpendingType() {
        return spendingType;
    }

    public void setSpendingType(String spendingType) {
        this.spendingType = spendingType;
    }
}
