package com.example.smartattendence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import Databases.DbHelper;
import Model.ListItem;

public class QRcode extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ListItem> arrayList;
    DbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_rcode);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        helper = new DbHelper(this);

        //fetch the data from database.... if it is available, show it using recyclerview adpter

        arrayList = helper.getAllInformation();

        if(arrayList.size()>0)
        {
            //data is available set is to adpter
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No data found",Toast.LENGTH_LONG).show();

        }


    }
}
