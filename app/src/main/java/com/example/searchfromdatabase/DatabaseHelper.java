package com.example.searchfromdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "Search.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Students(regNo text primary key,fullName text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists Students");
    }

    public boolean insertStudent(StudentModel student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("regNo", student.getRegNo());
        cv.put("fullName", student.getFullName());

        long result = db.insert("Students", null, cv);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor retrieveRecord(String regNo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from Students where regNo=?", new String[]{regNo});
       return cursor;
    }
}

