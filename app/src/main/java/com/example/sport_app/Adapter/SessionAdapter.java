package com.example.sport_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sport_app.Model.Session;
import com.example.sport_app.R;

import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.ItemViewHolder> {


    private final List<Session> listsToDo;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Session item, int position);
    }


    public SessionAdapter(List<Session> listsToDo, OnItemClickListener listener) {
        this.listsToDo = listsToDo;
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
        Session itemData = listsToDo.get(position);
        holder.bind(itemData, listener);

    }

    @Override
    public int getItemCount() {
        return listsToDo.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameView;
        private final TextView muscleView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.exerciseName);
            muscleView = itemView.findViewById(R.id.exerciseMuscle);
        }

        public void bind(final Session itemData, final OnItemClickListener listener) {
            //get title from Ressource

            nameView.setText(itemData.getName());
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
