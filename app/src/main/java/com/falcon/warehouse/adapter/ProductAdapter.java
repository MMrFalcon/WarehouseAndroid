package com.falcon.warehouse.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.falcon.warehouse.NavigationHost;
import com.falcon.warehouse.R;
import com.falcon.warehouse.entity.Product;
import com.falcon.warehouse.fragment.ProductAddEditFragment;
import com.falcon.warehouse.fragment.ProductListFragment;
import com.falcon.warehouse.root.Constants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> productList;
    private ProductListFragment productListFragment;

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

    public void attachFragment(ProductListFragment productListFragment) {
        this.productListFragment = productListFragment;
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

            edit.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                String productIndexCleanText = productIndex.getText().toString().replace("Index Produktu: ", "");
                bundle.putString(Constants.SCAN_PRODUCT_KEY, productIndexCleanText);
                bundle.putString(Constants.INPUT_TYPE, Constants.UPDATE);

                ProductAddEditFragment productAddEditFragment = new ProductAddEditFragment();
                productAddEditFragment.setArguments(bundle);

                ((NavigationHost) productListFragment.getActivity()).navigateTo(productAddEditFragment, true);
            });
        }
    }
}
