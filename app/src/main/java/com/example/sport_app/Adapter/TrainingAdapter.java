package com.example.sport_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sport_app.Model.Training;
import com.example.sport_app.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ItemViewHolder> {


    private final List<Training> mTrainings;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Training item, int position);
    }


    public TrainingAdapter(List<Training> trainings, OnItemClickListener listener) {
        this.mTrainings = trainings;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_training, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Training itemData = mTrainings.get(position);
        holder.bind(itemData, listener);

    }

    @Override
    public int getItemCount() {
        return mTrainings.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final LinearLayout trainingView;
        private final TextView trainingTitle;
        private final TextView trainingDate;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            trainingTitle = itemView.findViewById(R.id.title);
            trainingDate = itemView.findViewById(R.id.date);
            trainingView = itemView.findViewById(R.id.trainingView);
        }

        public void bind(final Training itemData, final OnItemClickListener listener) {
            //get title from Ressource
            trainingTitle.setText(itemData.getTrainingName());

            SimpleDateFormat sdf = new SimpleDateFormat("EEE d MMM");
            String currentDateandTime = sdf.format(itemData.getDate());
            trainingDate.setText(currentDateandTime);

            // Click listener
            trainingView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.onItemClick(itemData, position);
                }
            });
        }
    }
}
