package com.example.contactdataapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Contact_adapter extends RecyclerView.Adapter <Contact_adapter.holder>{

    List <Contact_table> contact_tableList = new ArrayList<Contact_table>();
    Context context;

    public Contact_adapter( List <Contact_table> contact_tableList, Context context) {

        /*
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "database-name").allowMainThreadQueries().build();

        Contact contact = db.contact();
        // contact.insertrecord(new Contact_table("Rt","9013377680","atul22singh@gmail.com"));
        contact_tableList =contact.getcontact();*/
        this.contact_tableList = contact_tableList;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
       View view = layoutInflater.inflate(R.layout.contact_row_layout,
                parent,
                false);
        return  new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
          holder.name.setText(contact_tableList.get(position).getName());
          holder.phone_no.setText(contact_tableList.get(position).getPhone_no());
          holder.email.setText(contact_tableList.get(position).getEmail_id());

          holder.RLayout.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {


                  AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();
                  Contact_detail contact_detail = new Contact_detail();

                  Bundle bundle = new Bundle();

                  Contact_table pass = contact_tableList.get(position);


                  bundle.putSerializable("data",pass);
                  contact_detail.setArguments(bundle);

                  appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.main_activity,contact_detail).addToBackStack(null).commit();

                 /* AppDatabase db = Room.databaseBuilder(context,
                          AppDatabase.class, "database-name").allowMainThreadQueries().build();

                  Contact contact = db.contact();
                  // contact.insertrecord(new Contact_table("Rt","9013377680","atul22singh@gmail.com"));
                  contact_tableList =contact.getcontact();

                  notifyItemChanged(position);
                  notifyDataSetChanged();



                          /*   Bundle bundle = new Bundle();
                bundle.putString("data","atul");
                ArrayList <Result> arrayList= (ArrayList<Result>) data.getResult();
                bundle.putSerializable("class", arrayList);
                ItemFragment fragment = new ItemFragment();
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().add(R.id.fragment,fragment).commit();
*/
              }
          });
    }

    @Override
    public int getItemCount() {
        return contact_tableList.size();
    }


    class holder extends RecyclerView.ViewHolder{

        TextView name,email,phone_no;
        RelativeLayout RLayout;

        public holder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            phone_no = (TextView) itemView.findViewById(R.id.phone_no);
            RLayout  = (RelativeLayout) itemView.findViewById(R.id.contact_row);
        }
    }
}
