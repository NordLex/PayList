package com.nordlex.paylist.data;

import android.content.ContentValues;

import java.util.ArrayList;

public class Query {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FinancialOperations.db";

    private static final String TABLE_INCOME = "Income";
    private static final String TABLE_OUTCOME = "Outcome";
    private static final String COLUMN_DOLLARS = "Doll_";
    private static final String COLUMN_CENTS = "Cent_";
    private static final String COLUMN_DATE = "Date";
    private static final String COLUMN_ID = "_id";

    private static final String TABLE_TOTAL = "Total";
    private static final String COLUMN_CLEAR = "Clear";
    private static final String COLUMN_INCOME = "Income";
    private static final String COLUMN_OUTCOME = "Outcome";


    public static String getDatabaseName () {
        return DATABASE_NAME;
    }

    public static int getDatabaseVersion () {
        return DATABASE_VERSION;
    }

    public static String getTableIncomeName () {
        return TABLE_INCOME;
    }

    public static String getTableOutcomeName () {
        return TABLE_OUTCOME;
    }

    public static String getTableTotalName () {
        return TABLE_TOTAL;
    }

    public static String createIncomeTable () {
        String query = "create table "+TABLE_INCOME+"("+
                COLUMN_ID+" integer primary key autoincrement, "+ COLUMN_DATE +" text unique, ";
        for (int counter=1;counter<(Explanation.incomeLength()-2);counter++) {
            query = query.concat(COLUMN_DOLLARS+counter+" int, ");
            query = query.concat(COLUMN_CENTS+counter+" int, ");
        }
        query = query.concat(COLUMN_DOLLARS+(Explanation.incomeLength()-1)+" int, ");
        query = query.concat(COLUMN_CENTS+(Explanation.incomeLength()-1)+" int )");
        return query;
    }

    public static String createOutcomeTable () {
        String query = "create table "+TABLE_OUTCOME+"("+
                COLUMN_ID+" integer primary key autoincrement, "+ COLUMN_DATE +" text unique, ";
        for (int cursor=1;cursor<(Explanation.incomeLength()-2);cursor++) {
            query = query.concat(COLUMN_DOLLARS+cursor+" int, ");
            query = query.concat(COLUMN_CENTS+cursor+" int, ");
        }
        query = query.concat(COLUMN_DOLLARS+(Explanation.outcomeLength()-1)+" int, ");
        query = query.concat(COLUMN_CENTS+(Explanation.outcomeLength()-1)+" int )");
        return query;
    }

    public static String createTotalTable () {
        return "create table "+TABLE_TOTAL+"("+COLUMN_ID+" integer primary key autoincrement, "+
                COLUMN_DATE+" text unique, "+ COLUMN_CLEAR +" text, "+
                COLUMN_INCOME+"text, "+COLUMN_OUTCOME+"text )";
    }

    public static ContentValues addRecord (String date, ArrayList<Item> numCodes) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DATE, date);
        for (Item item:numCodes) {
            cv.put(COLUMN_DOLLARS+item.getNumCode(), item.getDollars());
            cv.put(COLUMN_CENTS+item.getNumCode(), item.getCents());
        }
        return cv;
    }

    public static ContentValues addRecordToTotal (String date, Total total) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DATE,date);
        cv.put(COLUMN_CLEAR,total.getClear());
        cv.put(COLUMN_INCOME,total.getIncome());
        cv.put(COLUMN_OUTCOME,total.getOutcome());
        return cv;
    }

    public static String readRecordFromIncome() {
        return  "select * from "+TABLE_INCOME+" where "+COLUMN_DATE+" = ?";
    }

    public static String readRecordFromOutcome() {
        return  "select * from "+TABLE_OUTCOME+" where "+COLUMN_DATE+" = ?";
    }

    public static String readRecordFromTotal() {
        return  "select * from "+TABLE_TOTAL+" where "+COLUMN_DATE+" = ?";
    }
}
