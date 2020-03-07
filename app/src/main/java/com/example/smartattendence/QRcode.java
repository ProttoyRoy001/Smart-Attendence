package com.example.smartattendence;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

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

        new ItemTouchHelper(new ItemTouchHelper.Callback() {
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

    }
}
