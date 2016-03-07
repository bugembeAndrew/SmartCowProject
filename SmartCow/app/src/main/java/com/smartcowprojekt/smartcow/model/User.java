package com.smartcowprojekt.smartcow.model;

/**
 * Created by ANDREIS on 2/16/2016.
 */
public class User {

    private int user_id = 0;
    private String unique_id  = null;
    private String first_name  = null;
    private String last_name  = null;
    private String sex  = null;
    private String email  = null;
    private String telephone  = null;
    private String category  = null;
    private String encrypted_password  = null;
    private String salt  = null;
    private String created_at  = null;
    private String updated_at  = null;

    public User() {
    }

    public User(int user_id, String unique_id, String first_name, String last_name, String sex,
                String email, String telephone, String category, String encrypted_password,
                String salt, String created_at, String updated_at) {
        this.user_id = user_id;
        this.unique_id = unique_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.sex = sex;
        this.email = email;
        this.telephone =telephone;
        this.category = category;
        this.encrypted_password = encrypted_password;
        this.salt =salt;
        this.created_at =created_at;
        this.updated_at =updated_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEncrypted_password() {
        return encrypted_password;
    }

    public void setEncrypted_password(String encrypted_password) {
        this.encrypted_password = encrypted_password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
