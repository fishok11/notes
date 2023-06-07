package com.example.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NoteEditActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText contentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        titleEditText = findViewById(R.id.note_title_edit_text);
        contentEditText = findViewById(R.id.note_content_edit_text);
        Button saveButton = findViewById(R.id.save_button);
        Button cancelButton = findViewById(R.id.cancel_button);

        int noteId = getIntent().getExtras().getInt("noteId");
        String noteTitle = getIntent().getExtras().getString("noteTitle");
        String noteContent = getIntent().getExtras().getString("noteContent");

        titleEditText.setText(noteTitle);
        contentEditText.setText(noteContent);

        saveButton.setOnClickListener(view -> {
            String newTitle = titleEditText.getText().toString();
            String newContent = contentEditText.getText().toString();

            dbHelper.editNote(noteId, newTitle, newContent);

            MainActivity mainActivity = new MainActivity();
            mainActivity.refreshNotesList(this);

            finishAffinity();

            Intent intent = new Intent(NoteEditActivity.this, MainActivity.class);
            startActivity(intent);
        });

        cancelButton.setOnClickListener(view -> finish());
    }
}
