package com.nordlex.paylist.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private final Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, Query.getDatabaseName(), null, Query.getDatabaseVersion());
        this.context = context;
    }

    @Override
    public void onCreate (SQLiteDatabase database) {
        database.execSQL(Query.createIncomeTable());
        database.execSQL(Query.createOutcomeTable());
        database.execSQL(Query.createTotalTable());
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+Query.getTableIncomeName());
        db.execSQL("drop table if exists "+Query.getTableOutcomeName());
        db.execSQL("drop table if exists "+Query.getTableTotalName());
        onCreate(db);
    }

    private void addRecordToIncome (String date, ArrayList<Item> numCodes) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.insert(Query.getTableIncomeName(), null,Query.addRecord(date, numCodes));
        db.close();
        if (result == -1) {
            Toast.makeText(context, "Income Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Income Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    private void addRecordToOutcome (String date, ArrayList<Item> numCodes) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.insert(Query.getTableOutcomeName(), null,Query.addRecord(date, numCodes));
        db.close();
        if (result == -1) {
            Toast.makeText(context, "Outcome Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Outcome Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    private void addRecordToTotal (String date, Total total) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.insert(Query.getTableTotalName(), null,Query.addRecordToTotal(date, total));
        db.close();
        if (result == -1) {
            Toast.makeText(context, "Total Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Total Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void addRecord (String date, ArrayList<Item> items) {
        ArrayList<Item> income = new ArrayList<>();
        ArrayList<Item> outcome = new ArrayList<>();
        for (Item item:items) {
            if (item.getNumCode()>0 & item.getNumCode()<Explanation.incomeLength()) {
                income.add(item);
            } else if (item.getNumCode()>700 & item.getNumCode()<(Explanation.outcomeLength()+700)) {
                outcome.add(item);
            }
        }
        addRecordToIncome(date, income);
        addRecordToOutcome(date, outcome);
        addRecordToTotal(date, new Total(income, outcome));
    }

    public Cursor readRecordFromIncome(String date) {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor=database.rawQuery(Query.readRecordFromIncome(), new String[]{date});
            database.close();
        }
        return cursor;
    }

    public Cursor readRecordFromOutcome(String date) {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor=database.rawQuery(Query.readRecordFromOutcome(), new String[]{date});
            database.close();
        }
        return cursor;
    }

    public Cursor readRecordFromTotal(String date) {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor=database.rawQuery(Query.readRecordFromTotal(), new String[]{date});
            database.close();
        }
        return cursor;
    }
}