package com.nordlex.paylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.color.DynamicColors;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nordlex.paylist.data.DatabaseHelper;
import com.nordlex.paylist.data.Month;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView mainRecycler;
    FloatingActionButton addButton;

    DatabaseHelper database;
    MainAdapter adapter;

    ArrayList<String> date, clear, income, outcome;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (DynamicColors.isDynamicColorAvailable()) {
            DynamicColors.applyIfAvailable(this);
        }
        setContentView(R.layout.activity_main);

        mainRecycler = findViewById(R.id.main_recycler);
        addButton = findViewById(R.id.add_records_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        database = new DatabaseHelper(MainActivity.this);
        date = new ArrayList<>();
        clear = new ArrayList<>();
        income = new ArrayList<>();
        outcome = new ArrayList<>();

        storeDataInArrays();

        adapter = new MainAdapter(MainActivity.this, date, clear, income, outcome);
        mainRecycler.setAdapter(adapter);
        mainRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    protected void storeDataInArrays() {
        Cursor cursor = database.readAllRecordFromTotal();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String[] dateArr = (cursor.getString(1)).split("\\.");
                date.add(Month.byNumber(Integer.parseInt(dateArr[0].trim()))+" "+dateArr[1]);
                clear.add(cursor.getString(2));
                income.add(cursor.getString(3));
                outcome.add(cursor.getString(4));
            }
        }
    }
}