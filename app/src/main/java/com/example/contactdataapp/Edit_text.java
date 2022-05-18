package com.example.contactdataapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Edit_text extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public Edit_text() {
        // Required empty public constructor
    }

    EditText name,email,phone_number;
    Contact_table contact_table;
    Button submit;






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contact_table = (Contact_table) getArguments().getSerializable("data");
         String nag = getArguments().getString("nag");
        Toast.makeText(getContext(), nag, Toast.LENGTH_SHORT).show();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_text, container, false);
        name = (EditText) view.findViewById(R.id.editTextTextPersonName);
        email = (EditText) view.findViewById(R.id.editTextTextemail);
        phone_number = (EditText) view.findViewById(R.id.editTextTextPersonName3);
        submit = (Button) view.findViewById(R.id.submit);

        //setting name of the data
        name.setText(contact_table.getName());
        email.setText(contact_table.getEmail_id());
        phone_number.setText(contact_table.getPhone_no());


        //using button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "lagi aag ", Toast.LENGTH_SHORT).show();
                String new_name = name.getText().toString();
                String new_email = email.getText().toString();
                String new_phone = phone_number.getText().toString();

                AppDatabase db = Room.databaseBuilder(getContext(),
                        AppDatabase.class, "contact_db").allowMainThreadQueries().build();

                Contact contact = db.contact();
                contact.deletebyname(contact_table.getName());
                contact.insertrecord(new Contact_table(new_name,new_email,new_phone));
                //   view.change
                getActivity().onBackPressed();

              }
        });

        return view;
    }
}