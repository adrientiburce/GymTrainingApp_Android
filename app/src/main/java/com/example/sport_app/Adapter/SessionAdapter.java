package com.example.sport_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sport_app.Model.Exercise;
import com.example.sport_app.Model.Session;
import com.example.sport_app.R;

import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.ItemViewHolder> {


    private final List<Session> listsToDo;
    private final OnItemClickListener listener;
    private final OnItemDeleteClickListener listenerDelete;


    public interface OnItemClickListener {
        void onItemClick(Session item, int position);
    }

    public interface OnItemDeleteClickListener {
        void onItemClick(Session item, int position);
    }


    public SessionAdapter(List<Session> listsToDo, OnItemClickListener listener,  OnItemDeleteClickListener listenerDelete) {
        this.listsToDo = listsToDo;
        this.listener = listener;
        this.listenerDelete = listenerDelete;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_session, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Session itemData = listsToDo.get(position);
        holder.bind(itemData, listener, listenerDelete);

    }

    @Override
    public int getItemCount() {
        return listsToDo.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameView;
        private final TextView muscleView;
        private final TextView infosView;
        private final LinearLayout itemSessionView;
        private ImageButton deleteBtn;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.exerciseName);
            muscleView = itemView.findViewById(R.id.exerciseMuscle);
            infosView = itemView.findViewById(R.id.show_set_reps);
            itemSessionView = itemView.findViewById(R.id.click_session);
            deleteBtn = itemView.findViewById(R.id.btn_delete);

        }

        public void bind(final Session itemData, final OnItemClickListener listener, final OnItemDeleteClickListener listenerDelete) {
            //get title from Ressource
            nameView.setText(itemData.getExercise().getName());
            muscleView.setText(itemData.getExercise().getMuscle());
            infosView.setText(itemData.getInfos());

            // Click listener
            itemSessionView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    listener.onItemClick(itemData, position);
                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    removeAt(position);
                    listenerDelete.onItemClick(itemData, position);

                }
            });
        }

        public void removeAt(int position) {
            listsToDo.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, listsToDo.size());
        }
    }

}
