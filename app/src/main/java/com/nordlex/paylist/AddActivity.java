package com.nordlex.paylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nordlex.paylist.data.DatabaseHelper;
import com.nordlex.paylist.data.Item;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {
    RecyclerView addRecycler;
    Button buttonNewItem, buttonSave;
    EditText inputTextDate, inputTextCode, inputTextCash;
    ArrayList<Item> items;
    AddAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        items = new ArrayList<>();

        inputTextDate = findViewById(R.id.editTextDate);
        inputTextCode = findViewById(R.id.editTextNumCode);
        inputTextCash = findViewById(R.id.editTextCash);
        addRecycler = findViewById(R.id.add_recycler);
        buttonNewItem = findViewById(R.id.buttonNewItem);
        buttonSave = findViewById(R.id.buttonSave);

        buttonNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.add(setItem());
                adapter = new AddAdapter(AddActivity.this, items);
                addRecycler.setAdapter(adapter);
                addRecycler.setLayoutManager(new LinearLayoutManager(AddActivity.this));
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper database = new DatabaseHelper(AddActivity.this);
                database.addRecord(inputTextDate.getText().toString().trim(), items);
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //??Приведение строки в инты требует дополнительной проработки
    private Item setItem() {
        int intCode, intDollar, intCent;
        intCode = Integer.parseInt(inputTextCode.getText().toString().trim());
        String cash = inputTextCash.getText().toString().trim();
        String[] arrayCash = cash.split("\\.");
        intDollar = Integer.parseInt(arrayCash[0]);
        intCent = Integer.parseInt(arrayCash[1]);
        return new Item(intCode, intDollar, intCent);
    }
}