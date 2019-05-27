package com.example.sport_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sport_app.R;
import com.example.sport_app.Model.Exercise;

import java.util.List;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ItemViewHolder> {


    private final List<Exercise> mExercise;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Exercise item, int position);
    }


    public TrainingAdapter(List<Exercise> exercise, OnItemClickListener listener) {
        this.mExercise = exercise;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_task, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Exercise itemData = mExercise.get(position);
        holder.bind(itemData, listener);

    }

    @Override
    public int getItemCount() {
        return mExercise.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView exerciseTitle;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseTitle = itemView.findViewById(R.id.exerciseName);
        }

        public void bind(final Exercise itemData, final OnItemClickListener listener) {
            //get title from Ressource

            // Click listener
            exerciseTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.onItemClick(itemData, position);
                }
            });
        }
    }
}
