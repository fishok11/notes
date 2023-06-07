package com.example.main;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addNoteButton = findViewById(R.id.addNoteButton);
        ListView notesListView = findViewById(R.id.notesListView);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        try (SQLiteDatabase db = dbHelper.getWritableDatabase();
             Cursor cursor = db.rawQuery("SELECT  * FROM notes", null)) {

            ArrayList<Note> notes = new ArrayList<>();

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                    String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                    String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));

                    notes.add(new Note(id, title, content));
                }
            }

            adapter = new MyAdapter(this, notes);
            notesListView.setAdapter(adapter);

            notesListView.setOnItemClickListener((parent, view, position, id) -> {
                Intent intent = new Intent(MainActivity.this, NoteViewActivity.class);
                intent.putExtra("noteId", adapter.getItem(position).getId());
                intent.putExtra("noteTitle", adapter.getItem(position).getTitle());
                intent.putExtra("noteContent", adapter.getItem(position).getContent());
                startActivity(intent);
            });

        }

        addNoteButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
            startActivity(intent);
        });

    }

    public void refreshNotesList(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        try (SQLiteDatabase db = dbHelper.getWritableDatabase();
             Cursor cursor = db.rawQuery("SELECT * FROM notes", null)) {

            ArrayList<Note> notes = new ArrayList<>();

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                    String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                    String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));

                    notes.add(new Note(id ,title, content));
                }
            }

            notes.clear();
            adapter.addAll(notes);
            adapter.notifyDataSetChanged();

        }

    }

}
