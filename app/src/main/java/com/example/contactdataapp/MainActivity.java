package com.example.contactdataapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button add;
    Contact contact;
    Contact_adapter adapter;
    List < Contact_table >  arr = new ArrayList<Contact_table>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView = findViewById(R.id.recycle_contact);
        add = findViewById(R.id.add);


        //Edit_text fragment = new Edit_text();
        //getSupportFragmentManager().beginTransaction().add(R.id.main_activity,fragment).commit();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



               AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.main_activity,new Add_contact()).addToBackStack(null).commit();
              //  recyclerView.setAdapter(adapter);
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "contact_db").allowMainThreadQueries().build();

                Contact contact = db.contact();
                // contact.insertrecord(new Contact_table("Rt","9013377680","atul22singh@gmail.com"));
                List < Contact_table >  arri =contact.getcontact();
                adapter = new Contact_adapter(arri,getApplicationContext());
                recyclerView.setAdapter(adapter);

              //  adapter.notifyItemInserted(contact.getcontact().size()-1);
                adapter.notifyDataSetChanged();
            }
        });



        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "contact_db").allowMainThreadQueries().build();

        contact = db.contact();
        List < Contact_table >  arr =contact.getcontact();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Contact_adapter(arr,getApplicationContext());
        recyclerView.setAdapter(adapter);

    }
}