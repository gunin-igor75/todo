package ru.gil.todo_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<Note> notes = new ArrayList<>();

    private OnNoteClickListener onNoteClickListener;


    public List<Note> getNotes() {
        return new ArrayList<>(notes);
    }

    public void setOnNoteClickListener(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.note_item,
                parent,
                false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.textView.setText(note.getText());
        holder.textView.setBackgroundColor(getIdColor(note.getPriority(), holder));
        holder.itemView.setOnClickListener(view -> {
            if (onNoteClickListener != null) {
                onNoteClickListener.onNoteClick(note);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    private int getIdColor(int priority, NoteViewHolder holder) {
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
        return ContextCompat.getColor(holder.itemView.getContext(), idColor);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{

        private  TextView textView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewNote);
        }
    }

    interface OnNoteClickListener{
        void onNoteClick(Note note);
    }
}
