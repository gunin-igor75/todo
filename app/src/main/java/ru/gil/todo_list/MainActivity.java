package ru.gil.todo_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayoutNotes;
    private FloatingActionButton buttonAddNote;
    private Database database = Database.getInstance();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        buttonAddNote.setOnClickListener(view ->{
            Intent intent = AddNoteActivity.newIntent(MainActivity.this);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showNotes();
    }

    private void initViews() {
        linearLayoutNotes = findViewById(R.id.linearLayoutNotes);
        buttonAddNote = findViewById(R.id.buttonAddNote);
    }


    private void showNotes() {
        linearLayoutNotes.removeAllViews();
        for (Note note : database.getNotes()) {
            View view = getLayoutInflater().inflate(
                    R.layout.note_item,
                    linearLayoutNotes,
                    false);
            view.setOnClickListener(view1 -> {
                        database.remove(note.getId());
                        showNotes();
                    }
            );
            TextView textView = view.findViewById(R.id.textViewNote);
            textView.setText(note.getText());
            textView.setBackgroundColor(getIdColor(note.getPriority()));
            linearLayoutNotes.addView(view);
        }
    }

    private int getIdColor(int priority) {
        int idColor;
        switch (priority) {
            case 0:
                idColor = android.R.color.holo_green_light;
                break;
            case 1:
                idColor = android.R.color.holo_orange_light;
                break;
            default:
                idColor = android.R.color.holo_red_light;
        }
        return ContextCompat.getColor(this, idColor);
    }
}