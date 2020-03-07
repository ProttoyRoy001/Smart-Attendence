package com.example.smartattendence;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

import Adapters.MyAdapter;
import Databases.DbHelper;
import Model.ListItem;

public class QRcode extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ListItem> arrayList;
    MyAdapter myAdapter;
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
            myAdapter = new MyAdapter(arrayList,this);
            recyclerView.setAdapter(myAdapter);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No data found",Toast.LENGTH_LONG).show();

        }
         //on swipe left or right to removie the data

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return 0;
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                final int postion = viewHolder.getAdapterPosition();
                ListItem listItem =  arrayList.get(postion);
                // remove data

                helper.deletRow(listItem.getId());
                arrayList.remove(postion);
                myAdapter.notifyItemRemoved(postion);
                myAdapter.notifyItemRangeChanged(postion,arrayList.size());



            }
        }).attachToRecyclerView(recyclerView);

        final IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setCameraId(0);


        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentIntegrator.initiateScan();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null)
        {
            if(result.getContents() == null)
            {
                Toast.makeText(getApplicationContext(),"No Result Found",Toast.LENGTH_SHORT).show();
            }
            else {
                 boolean isInserted = helper.insertData(result.getFormatName(),result.getContents());
                 if (isInserted)
                 {
                     arrayList.clear();
                     arrayList = helper.getAllInformation();
                     myAdapter = new MyAdapter(arrayList,this);
                     recyclerView.setAdapter(myAdapter);
                     myAdapter.notifyDataSetChanged();
                 }
            }
        }
        else
        {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}
