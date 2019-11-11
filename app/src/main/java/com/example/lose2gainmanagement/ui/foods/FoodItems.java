package com.example.lose2gainmanagement.ui.foods;

import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

@Entity
public class FoodItems{
    @PrimaryKey(autoGenerate = true)
    public int fId;

    public String fName;
    public String fAmount;
    public String fQuantity;
    public String fCallories;
    public String fProten;
    public String fFat;
    public String fCarb;

    public FoodItems( String fName, String fAmount, String fQuantity, String fCallories, String fProten, String fFat, String fCarb) {
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

    public String getfAmount() {
        return fAmount;
    }

    public void setfAmount(String fAmount) {
        this.fAmount = fAmount;
    }

    public String getfQuantity() {
        return fQuantity;
    }

    public void setfQuantity(String fQuantity) {
        this.fQuantity = fQuantity;
    }

    public String getfCallories() {
        return fCallories;
    }

    public void setfCallories(String fCallories) {
        this.fCallories = fCallories;
    }

    public String getfProten() {
        return fProten;
    }

    public void setfProten(String fProten) {
        this.fProten = fProten;
    }

    public String getfFat() {
        return fFat;
    }

    public void setfFat(String fFat) {
        this.fFat = fFat;
    }

    public String getfCarb() {
        return fCarb;
    }

    public void setfCarb(String fCarb) {
        this.fCarb = fCarb;
    }
}
