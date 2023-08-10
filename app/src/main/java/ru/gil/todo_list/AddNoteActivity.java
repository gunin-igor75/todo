package ru.gil.todo_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextNote;
    private RadioButton radioButtonLow;
    private RadioButton radioButtonMedium;
    private Button buttonSave;
    private Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initViews();
        buttonSave.setOnClickListener(view -> saveNote());
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, AddNoteActivity.class);
    }

    private void initViews() {
        editTextNote = findViewById(R.id.editTextNote);
        radioButtonLow = findViewById(R.id.radioButtonLow);
        radioButtonMedium = findViewById(R.id.radioButtonMedium);
        buttonSave = findViewById(R.id.buttonSave);
    }

    private void saveNote() {
        String text = editTextNote.getText().toString().trim();
        if (text.isEmpty()) {
            Toast.makeText(
                    AddNoteActivity.this,
                    R.string.error_field_empty,
                    Toast.LENGTH_SHORT
            ).show();
        } else {
            int priority = getPriority();
            int id = database.getNotes().size();
            Note note = new Note(id, text, priority);
            database.add(note);
            finish();
        }
    }


    private int getPriority() {
        if (radioButtonLow.isChecked()) {
            return 0;
        } else if (radioButtonMedium.isChecked()) {
            return 1;
        } else {
            return 2;
        }
    }
}