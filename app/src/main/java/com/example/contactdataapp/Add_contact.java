package com.example.contactdataapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Add_contact extends Fragment {

    EditText name,email,phone_number;
    Button submit;

    public Add_contact() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  =  inflater.inflate(R.layout.fragment_add_contact, container, false);

        view.setBackgroundColor(Color.WHITE);

        name = (EditText) view.findViewById(R.id.editTextTextPersonName);
        email = (EditText) view.findViewById(R.id.editTextTextemail);
        phone_number = (EditText) view.findViewById(R.id.editTextTextPersonName3);
        submit = (Button) view.findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_name = name.getText().toString();
                String new_email = email.getText().toString();
                String new_phone = phone_number.getText().toString();

                AppDatabase db = Room.databaseBuilder(getContext(),
                        AppDatabase.class, "contact_db").allowMainThreadQueries().build();

                Contact contact = db.contact();
                contact.insertrecord(new Contact_table(new_name,new_phone,new_email));
                /*RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_contact);
                Contact_adapter adapter = new Contact_adapter(contact.getcontact(),getActivity().getApplicationContext());
                adapter.notifyDataSetChanged();
                //recyclerView.setAdapter(adapter);*/
            }

        });

        return  view;

    }
}