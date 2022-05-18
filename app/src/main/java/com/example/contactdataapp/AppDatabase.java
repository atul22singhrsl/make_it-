package com.example.contactdataapp;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact_table.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract Contact contact();

}