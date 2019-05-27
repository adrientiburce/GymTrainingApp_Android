package com.example.sport_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sport_app.Model.Training;
import com.example.sport_app.R;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ItemViewHolder> {


    private final List<Training> listsToDo;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Training item, int position);
    }


    public ExerciseAdapter(List<Training> listsToDo, OnItemClickListener listener) {
        this.listsToDo = listsToDo;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_list, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Training itemData = listsToDo.get(position);
        holder.bind(itemData, listener);

    }

    @Override
    public int getItemCount() {
        return listsToDo.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title);
        }

        public void bind(final Training itemData, final OnItemClickListener listener) {
            //get title from Ressource
            textView.setText(itemData.getTrainingName());

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
