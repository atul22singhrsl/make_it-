package com.example.contactdataapp;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity
public class Contact_table implements Serializable {

    @PrimaryKey
    @NotNull
    public String name;

    @ColumnInfo (name = "Phone_no")
    public String phone_no;

    @ColumnInfo (name = "Email_id")
    public String email_id;


    public Contact_table(@NotNull String name, String phone_no, String email_id) {
        this.name = name;
        this.phone_no = phone_no;
        this.email_id = email_id;
    }

    public  Contact_table()
    {
        this.name="atul";

    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }
}
