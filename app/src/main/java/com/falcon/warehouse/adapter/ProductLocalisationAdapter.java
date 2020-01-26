package com.falcon.warehouse.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.falcon.warehouse.R;
import com.falcon.warehouse.entity.ProductLocalisation;
import com.google.android.material.textview.MaterialTextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductLocalisationAdapter extends RecyclerView.Adapter<ProductLocalisationAdapter.ViewHolder> {

    private List<ProductLocalisation> productLocalisations;

    public ProductLocalisationAdapter() {
        this.productLocalisations = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_localisation_card, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductLocalisation productLocalisation = productLocalisations.get(position);
        DecimalFormat df = new DecimalFormat("#,###.00");

        holder.productIndex.setText("Index Produktu: " + productLocalisation.getProductIndex());
        holder.localisationIndex.setText("Index Lokalizacji: " + productLocalisation.getLocalisationIndex());
        holder.quantity.setText("Produkty w Lokalizacji: " + df.format(productLocalisation.getQuantity()));
        holder.productLocalisationId.setText("Numer Encji: " + productLocalisation.getId());
    }

    @Override
    public int getItemCount() {
        return productLocalisations.size();
    }

    public void addItems(List<ProductLocalisation> items) {
        this.productLocalisations = items;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public MaterialTextView productIndex;
        public MaterialTextView localisationIndex;
        public MaterialTextView productLocalisationId;
        public MaterialTextView quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productIndex = itemView.findViewById(R.id.prodIndex);
            localisationIndex = itemView.findViewById(R.id.locIndex);
            quantity = itemView.findViewById(R.id.prodLocQuantity);
            productLocalisationId = itemView.findViewById(R.id.prodLocId);
        }
    }
}
