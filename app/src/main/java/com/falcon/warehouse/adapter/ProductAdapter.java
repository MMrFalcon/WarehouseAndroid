package com.falcon.warehouse.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.falcon.warehouse.R;
import com.falcon.warehouse.entity.Product;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> productList;

    public ProductAdapter() {
        this.productList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        DecimalFormat df = new DecimalFormat("#,###.00");

        holder.productIndex.setText("Index Produktu: " + product.getProductIndex());
        holder.productId.setText("ID: " + product.getId());
        holder.quantity.setText("Ilość: " + df.format(product.getQuantity()));
        holder.productName.setText("Nazwa: " + product.getProductName());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void addItems(List<Product> items) {
        this.productList = items;
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public MaterialTextView productId;
        public MaterialTextView productIndex;
        public MaterialTextView productName;
        public MaterialTextView quantity;
        public MaterialButton edit;
        public MaterialButton remove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productId = itemView.findViewById(R.id.productId);
            productIndex = itemView.findViewById(R.id.productIndex);
            productName = itemView.findViewById(R.id.productName);
            quantity = itemView.findViewById(R.id.productQuantity);
            edit = itemView.findViewById(R.id.editProd);
            remove = itemView.findViewById(R.id.removeProd);
        }
    }
}
