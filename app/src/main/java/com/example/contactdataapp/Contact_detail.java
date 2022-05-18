package com.example.contactdataapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.temporal.Temporal;


public class Contact_detail extends Fragment {

    Contact_table contact_table;
    TextView name,email,phone_no;
    Button call,mail,delete,edit;

    public Contact_detail() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contact_table = (Contact_table) getArguments().getSerializable("data");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_contact_detail, container, false);

        name = (TextView) view.findViewById(R.id.textView);
        email = (TextView) view.findViewById(R.id.textView2);
        phone_no = (TextView) view.findViewById(R.id.textView3);
        edit = (Button) view.findViewById(R.id.edit);
        delete = (Button) view.findViewById(R.id.delete);
        mail = (Button) view.findViewById(R.id.email);
        call = (Button) view.findViewById(R.id.call);


        name.setText(contact_table.getName());
        email.setText(contact_table.getEmail_id());
        phone_no.setText(contact_table.getPhone_no());






        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();
                Edit_text edit_Text = new Edit_text();

                Bundle bundle = new Bundle();
                bundle.putSerializable("data",contact_table);
                edit_Text.setArguments(bundle);

                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.main_activity,edit_Text).addToBackStack(null).commit();

            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+contact_table.getPhone_no()));
                startActivity(callIntent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppDatabase db = Room.databaseBuilder(getContext(),
                        AppDatabase.class, "contact_db").allowMainThreadQueries().build();

                Contact contact = db.contact();

                contact.deletebyname(contact_table.getName());
                getActivity().onBackPressed();


            }
        });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {contact_table.getEmail_id() });
                intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                intent.putExtra(Intent.EXTRA_TEXT, "mail body");
                startActivity(Intent.createChooser(intent, ""));
            }
        });



       return view;
    }
}