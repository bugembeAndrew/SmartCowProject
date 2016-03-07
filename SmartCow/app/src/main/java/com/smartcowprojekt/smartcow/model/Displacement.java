package com.smartcowprojekt.smartcow.model;

/**
 * Created by ANDREIS on 2/16/2016.
 */
public class Displacement {

    private  int displacement_id = 0;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private double speed = 0.0;
    private int cattle = 0;
    private String created_at = null;
    private String updated_at = null;

    public Displacement() {
    }

    public Displacement(int displacement_id, double latitude, double longitude, double speed, int cattle,
                        String created_at, String updated_at) {
        this.displacement_id = displacement_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.cattle = cattle;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getDisplacement_id() {
        return displacement_id;
    }

    public void setDisplacement_id(int displacement_id) {
        this.displacement_id = displacement_id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getCattle() {
        return cattle;
    }

    public void setCattle(int cattle) {
        this.cattle = cattle;
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
