package com.example.contactdataapp;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;

import java.util.List;

@Dao
public interface Contact {

    @Insert
    void insertrecord(Contact_table contact_table);

    @Query("SELECT EXISTS (SELECT * FROM CONTACT_TABLE WHERE name=:name_check)")
    Boolean is_exist(String name_check);

    @Query("SELECT * FROM contact_table")
    List < Contact_table > getcontact();

    @Query("DELETE FROM contact_table WHERE name=:name_check")
    void deletebyname(String name_check);

}



