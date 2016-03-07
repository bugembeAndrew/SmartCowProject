package com.smartcowprojekt.smartcow.model;

/**
 * Created by ANDREIS on 2/16/2016.
 */
public class Farm {

    private int farm_id = 0;
    private String farmName  = null;
    private int farmer = 0;
    private String created_at  = null;
    private String updated_at  = null;

    public Farm() {
    }

    public Farm(int farm_id, String farmName, int farmer, String created_at, String updated_at) {
        this.farm_id = farm_id;
        this.farmName = farmName;
        this.farmer = farmer;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getFarm_id() {
        return farm_id;
    }

    public void setFarm_id(int farm_id) {
        this.farm_id = farm_id;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public int getFarmer() {
        return farmer;
    }

    public void setFarmer(int farmer) {
        this.farmer = farmer;
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
