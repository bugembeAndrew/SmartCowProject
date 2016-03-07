package com.smartcowprojekt.smartcow.model;

/**
 * Created by ANDREIS on 2/16/2016.
 */
public class Cattle {

    private  int cattle_id = 0;
    private String cattleName = null;
    private String weight = null;
    private String sex = null;
    private String dateOfBirth = null;
    private String breed = null;
    private double litresOfMilk = 0.0;
    private int offSpring = 0;
    private int farm = 0;
    private String created_at = null;
    private String updated_at = null;

    public Cattle() {
    }

    public Cattle(int cattle_id, String cattleName, String weight, String sex, String dateOfBirth,
                  String breed, double litresOfMilk, int offSpring, int farm, String created_at,
                  String updated_at) {
        this.cattle_id = cattle_id;
        this.cattleName = cattleName;
        this.weight = weight;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.breed = breed;
        this.litresOfMilk = litresOfMilk;
        this.offSpring = offSpring;
        this.farm = farm;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getCattle_id() {
        return cattle_id;
    }

    public void setCattle_id(int cattle_id) {
        this.cattle_id = cattle_id;
    }

    public String getCattleName() {
        return cattleName;
    }

    public void setCattleName(String cattleName) {
        this.cattleName = cattleName;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public double getLitresOfMilk() {
        return litresOfMilk;
    }

    public void setLitresOfMilk(double litresOfMilk) {
        this.litresOfMilk = litresOfMilk;
    }

    public int getOffSpring() {
        return offSpring;
    }

    public void setOffSpring(int offSpring) {
        this.offSpring = offSpring;
    }

    public int getFarm() {
        return farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
