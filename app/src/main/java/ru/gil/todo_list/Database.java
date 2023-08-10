package ru.gil.todo_list;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Database {
    private Random random = new Random();
    private List<Note> notes = new ArrayList<>();
    private static Database database;

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    private Database() {

        for (int i = 0; i < 5; i++) {
            Note note = new Note(
                    i,
                    "Note " + i,
                    random.nextInt(3)
            );
            notes.add(note);
        }
    }

    public void add(Note note) {
        notes.add(note);
    }

    public void remove(int id) {
        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            if (note.getId() == id) {
                notes.remove(note);
                break;
            }
        }
    }

    public List<Note> getNotes() {
        return new ArrayList<>(notes);
    }
}
