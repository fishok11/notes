package com.example.main;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mydb.sqlite";
    public static final int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE notes (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notes;");
        onCreate(db);
    }

    public boolean addNote(String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("content", content);

        long result = db.insert("notes", null, values);

        db.close();

        return result != -1;
    }

    public void deleteNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("notes", "_id=?", new String[]{String.valueOf(id)});

        db.close();
    }

    public void editNote(int id, String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("content", content);

        db.update("notes", values, "_id=?", new String[]{String.valueOf(id)});

        db.close();
    }
}

