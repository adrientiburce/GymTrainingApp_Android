package com.example.sport_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sport_app.Model.Exercise;
import com.example.sport_app.R;

import java.util.ArrayList;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ItemViewHolder> {


    private final ArrayList<Exercise> listExercises;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Exercise item, int position);
    }


    public ExercisesAdapter(ArrayList<Exercise> listExercises, OnItemClickListener listener) {
        this.listExercises = listExercises;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_exercise, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Exercise itemData = listExercises.get(position);
        holder.bind(itemData, listener);

    }

    @Override
    public int getItemCount() {
        return listExercises.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView textNameView;
        private final TextView textMuscleView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textNameView = itemView.findViewById(R.id.txt_exercise);
            textMuscleView = itemView.findViewById(R.id.txt_muscle);
        }

        public void bind(final Exercise itemData, final OnItemClickListener listener) {
            //get title from Ressource
            textNameView.setText(itemData.getName());
            textMuscleView.setText(itemData.getMuscle());

            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    listener.onItemClick(itemData, position);
                }
            });
        }
    }
}
