package com.example.lose2gainmanagement.ui.form.clientDatabase;


import android.graphics.Bitmap;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "clients")
public class ClientEntity implements Serializable {

    public ClientEntity(String name, String occupation, String phone_no, String address, String height, String weight, String sex, String age, String waist, String wrist, String chest, String hip, String foreArm, String bicept_left, String bicept_right, String thigh_left, String thigh_right, String food_preferred, String food_allergic, String food_fav, String client_added_date, String packages, String client_image_directory, String priority, String running_week, String next_followup, String client_image, String last_followup) {
        this.name = name;
        this.occupation = occupation;
        this.phone_no = phone_no;
        this.address = address;
        this.height = height;
        this.weight = weight;
        this.sex = sex;
        this.age = age;
        this.waist = waist;
        this.wrist = wrist;
        this.chest = chest;
        this.hip = hip;
        this.foreArm = foreArm;
        this.bicept_left = bicept_left;
        this.bicept_right = bicept_right;
        this.thigh_left = thigh_left;
        this.thigh_right = thigh_right;
        this.food_preferred = food_preferred;
        this.food_allergic = food_allergic;
        this.food_fav = food_fav;
        this.client_added_date = client_added_date;
        this.packages = packages;
        this.client_image_directory = client_image_directory;
        this.client_image = client_image;
        this.priority = priority;
        this.running_week = running_week;
        this.next_followup = next_followup;
        this.last_followup = last_followup;
    }

    @PrimaryKey(autoGenerate = true)
    private long client_id;



    private String name;
    private String occupation;
    private String phone_no;
    private String address;
    private String height;
    private String weight; //optional
    private String sex;
    private String age;

    private String waist; //optional
    private String wrist;
    private String chest; //optional
    private String hip; //optional
    private String foreArm;
    private String bicept_left;
    private String bicept_right;
    private String thigh_left;
    private String thigh_right;

    private String food_preferred;
    private String food_allergic;
    private String food_fav;
    private String client_added_date;
    private String packages;

    private String client_image_directory;
    private String client_image;


    private String priority;
    private String running_week;
    private String next_followup;
    private String last_followup;



    //Getters
    public long getClient_id() {
        return client_id;
    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getAddress() {
        return address;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getSex() {
        return sex;
    }

    public String getAge() {
        return age;
    }

    public String getWaist() {
        return waist;
    }

    public String getWrist() {
        return wrist;
    }

    public String getChest() {
        return chest;
    }

    public String getHip() {
        return hip;
    }

    public String getForeArm() {
        return foreArm;
    }

    public String getBicept_left() {
        return bicept_left;
    }

    public String getBicept_right() {
        return bicept_right;
    }

    public String getThigh_left() {
        return thigh_left;
    }

    public String getThigh_right() {
        return thigh_right;
    }

    public String getFood_preferred() {
        return food_preferred;
    }

    public String getFood_allergic() {
        return food_allergic;
    }

    public String getFood_fav() {
        return food_fav;
    }

    public String getClient_added_date() {
        return client_added_date;
    }

    public String getPackages() {
        return packages;
    }

    public String getClient_image_directory() {
        return client_image_directory;
    }

    public String getClient_image() {
        return client_image;
    }

    public String getPriority() {
        return priority;
    }

    public String getRunning_week() {
        return running_week;
    }

    public String getNext_followup() {
        return next_followup;
    }

    public String getLast_followup() {
        return last_followup;
    }

    //Setters


    public void setName(String name) {
        this.name = name;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public void setWrist(String wrist) {
        this.wrist = wrist;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public void setHip(String hip) {
        this.hip = hip;
    }

    public void setForeArm(String foreArm) {
        this.foreArm = foreArm;
    }

    public void setBicept_left(String bicept_left) {
        this.bicept_left = bicept_left;
    }

    public void setBicept_right(String bicept_right) {
        this.bicept_right = bicept_right;
    }

    public void setThigh_left(String thigh_left) {
        this.thigh_left = thigh_left;
    }

    public void setThigh_right(String thigh_right) {
        this.thigh_right = thigh_right;
    }

    public void setFood_preferred(String food_preferred) {
        this.food_preferred = food_preferred;
    }

    public void setFood_allergic(String food_allergic) {
        this.food_allergic = food_allergic;
    }

    public void setClient_image(String client_image) {
        this.client_image = client_image;
    }

    public void setFood_fav(String food_fav) {
        this.food_fav = food_fav;
    }

    public void setClient_added_date(String client_added_date) {
        this.client_added_date = client_added_date;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public void setClient_image_directory(String client_image_directory) {
        this.client_image_directory = client_image_directory;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setRunning_week(String running_week) {
        this.running_week = running_week;
    }

    public void setNext_followup(String next_followup) {
        this.next_followup = next_followup;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public void setLast_followup(String last_followup) {
        this.last_followup = last_followup;
    }
}
