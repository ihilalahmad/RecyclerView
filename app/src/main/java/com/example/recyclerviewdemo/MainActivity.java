package com.example.recyclerviewdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;


import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Details> detailsList;
    MyAdapter myAdapter;
    ConstraintLayout mLayout;//for snackbar


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        mLayout = findViewById(R.id.mLayout);
        detailsList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //passing data to model class
        for (int i=0; i<=10; i++){//i am using for loop to repeat data as i have no multiple detailss

            Details details = new Details("Java " + i , "This is description " + i);
            detailsList.add(details);//hello this is the android recycler view tutorial in this tutorial you will learn all about adroid adsadsad
        }

        myAdapter = new MyAdapter(detailsList, new MyAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Details details) {
                showToast(details.getDescription() + " Clicked!");
            }
        });
        recyclerView.setAdapter(myAdapter);

        //another itemtouchhelper
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);//pass your callback here
        itemTouchHelper.attachToRecyclerView(recyclerView);//pass your recyclerview here. Now run the app
    }

    //creating itemtouch helper call back
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            detailsList.remove(viewHolder.getAdapterPosition());
            myAdapter.notifyDataSetChanged();
            Snackbar.make(MainActivity.this, mLayout, "Item deleted.",Snackbar.LENGTH_SHORT).show();
        }
    };

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}