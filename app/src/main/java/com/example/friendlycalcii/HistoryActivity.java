package com.example.friendlycalcii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import Model.ListItem;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ListItem> listItems;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerviewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
        for(int x =1 ; x< 12 ; x++){
            ListItem listitem = new ListItem(
                    "Muhammed" + (x+1),
                    "Details",
                    "23"
            );

            listItems.add(listitem);
        }

        adapter = new MyAdapter(this,listItems);
        recyclerView.setAdapter(adapter);
    }

}