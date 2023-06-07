package com.example.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class NoteViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_view);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        TextView noteTitleTextView = findViewById(R.id.note_title_text_view);
        TextView noteContentTextView = findViewById(R.id.note_content_text_view);
        Button editNoteButton = findViewById(R.id.edit_note_button);
        Button deleteNoteButton = findViewById(R.id.delete_note_button);
        Button backButton = findViewById(R.id.back_view_button);

        int noteId = getIntent().getExtras().getInt("noteId");
        String noteTitle = getIntent().getExtras().getString("noteTitle");
        String noteContent = getIntent().getExtras().getString("noteContent");

        noteTitleTextView.setText(noteTitle);
        noteContentTextView.setText(noteContent);

        editNoteButton.setOnClickListener(view -> {
            Intent intent = new Intent(NoteViewActivity.this, NoteEditActivity.class);
            intent.putExtra("noteId", noteId);
            intent.putExtra("noteTitle", noteTitle);
            intent.putExtra("noteContent", noteContent);
            startActivity(intent);
        });

        deleteNoteButton.setOnClickListener(view -> {
            dbHelper.deleteNote(noteId);

            MainActivity mainActivity = new MainActivity();
            mainActivity.refreshNotesList(this);

            finish();
        });

        backButton.setOnClickListener(view -> finish());
    }
}

