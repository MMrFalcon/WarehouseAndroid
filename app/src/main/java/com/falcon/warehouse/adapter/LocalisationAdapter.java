package com.falcon.warehouse.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.falcon.warehouse.R;
import com.falcon.warehouse.entity.Localisation;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class LocalisationAdapter extends RecyclerView.Adapter<LocalisationAdapter.ViewHolder> {

    private List<Localisation> localisations;

    public LocalisationAdapter() {
        this.localisations = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.localisation_card, parent, false);
        return new LocalisationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Localisation localisation = localisations.get(position);

        holder.localisationIndex.setText("Index Lokalizacji: " + localisation.getLocalisationIndex());
        holder.localisationId.setText("ID: " + localisation.getId());
        holder.localisationName.setText("Nazwa: " + localisation.getLocalisationName());
    }

    @Override
    public int getItemCount() {
        return localisations.size();
    }

    public void addItems(List<Localisation> items) {
        this.localisations = items;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public MaterialTextView localisationId;
        public MaterialTextView localisationIndex;
        public MaterialTextView localisationName;
        public MaterialButton edit;
        public MaterialButton remove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            localisationId = itemView.findViewById(R.id.localisationId);
            localisationIndex = itemView.findViewById(R.id.localisationIndex);
            localisationName = itemView.findViewById(R.id.localisationName);
            edit = itemView.findViewById(R.id.editLoc);
            remove = itemView.findViewById(R.id.removeLoc);
        }
    }
}
