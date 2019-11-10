package com.example.lose2gainmanagement.ui.foods;

import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

@Entity
public class FoodItems{
    @PrimaryKey(autoGenerate = true)
    public int fId;

    public String fName;
    public int fAmount;
    public String fQuantity;
    public double fCallories;
    public double fProten;
    public double fFat;
    public double fCarb;

    public FoodItems( String fName, int fAmount, String fQuantity, double fCallories, double fProten, double fFat, double fCarb) {
        this.fName = fName;
        this.fAmount = fAmount;
        this.fQuantity = fQuantity;
        this.fCallories = fCallories;
        this.fProten = fProten;
        this.fFat = fFat;
        this.fCarb = fCarb;
    }

    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public int getfAmount() {
        return fAmount;
    }

    public void setfAmount(int fAmount) {
        this.fAmount = fAmount;
    }

    public String getfQuantity() {
        return fQuantity;
    }

    public void setfQuantity(String fQuantity) {
        this.fQuantity = fQuantity;
    }

    public double getfCallories() {
        return fCallories;
    }

    public void setfCallories(double fCallories) {
        this.fCallories = fCallories;
    }

    public double getfProten() {
        return fProten;
    }

    public void setfProten(double fProten) {
        this.fProten = fProten;
    }

    public double getfFat() {
        return fFat;
    }

    public void setfFat(double fFat) {
        this.fFat = fFat;
    }

    public double getfCarb() {
        return fCarb;
    }

    public void setfCarb(double fCarb) {
        this.fCarb = fCarb;
    }
}
