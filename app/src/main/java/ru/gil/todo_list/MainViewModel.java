package ru.gil.todo_list;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private NotesDao notesDao;

    private int count;

    private MutableLiveData<Integer> liveData = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        notesDao = NoteDatabase.getInstance(application).notesDao();
    }

    public LiveData<List<Note>> getNotes() {
        return notesDao.getNotes();
    }

    public LiveData<Integer> getCount() {
        return liveData;
    }

    public void showCount() {
        count++;
        liveData.setValue(count);
    }
    public void remove(Note note) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                notesDao.remove(note.getId());
            }
        });
        thread.start();
    }
}
