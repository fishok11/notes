package com.example.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateNoteActivity extends AppCompatActivity {

    private EditText noteTitleEditText, noteContentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        noteTitleEditText = findViewById(R.id.title_edit_text);
        noteContentEditText = findViewById(R.id.content_edit_text);
        Button saveButton = findViewById(R.id.save_button);
        Button backButton = findViewById(R.id.back_button);

        saveButton.setOnClickListener(v -> {
            String title = noteTitleEditText.getText().toString();
            String content = noteContentEditText.getText().toString();

            dbHelper.addNote(title, content);

            MainActivity mainActivity = new MainActivity();
            mainActivity.refreshNotesList(this);

            finish();
        });

        backButton.setOnClickListener(v -> finish());
    }
}