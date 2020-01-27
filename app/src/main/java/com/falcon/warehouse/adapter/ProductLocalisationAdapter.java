package com.falcon.warehouse.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.falcon.warehouse.NavigationHost;
import com.falcon.warehouse.R;
import com.falcon.warehouse.entity.ProductLocalisation;
import com.falcon.warehouse.fragment.ProductLocalisationListFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductLocalisationAdapter extends RecyclerView.Adapter<ProductLocalisationAdapter.ViewHolder> {

    private List<ProductLocalisation> productLocalisations;
    private ProductLocalisationListFragment productLocalisationListFragment;

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

        holder.productIndex.setText("Index Produktu: " + productLocalisation.getProductIndex());
        holder.localisationIndex.setText("Index Lokalizacji: " + productLocalisation.getLocalisationIndex());
        holder.quantity.setText(productLocalisation.getQuantity().toString());
        holder.productLocalisationId.setText("Numer Encji: " + productLocalisation.getId());
        holder.productId = productLocalisation.getProductId();
        holder.localisationId = productLocalisation.getLocalisationId();
    }

    @Override
    public int getItemCount() {
        return productLocalisations.size();
    }

    public void addItems(List<ProductLocalisation> items) {
        this.productLocalisations = items;
        notifyDataSetChanged();
    }

    public void attachFragment(ProductLocalisationListFragment productLocalisationListFragment) {
        this.productLocalisationListFragment = productLocalisationListFragment;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public MaterialTextView productIndex;
        public MaterialTextView localisationIndex;
        public MaterialTextView productLocalisationId;
        public TextInputEditText quantity;
        public MaterialButton updateProductLocalisation;

        private Long productId;
        private Long localisationId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productIndex = itemView.findViewById(R.id.prodIndex);
            localisationIndex = itemView.findViewById(R.id.locIndex);
            quantity = itemView.findViewById(R.id.prodLocQuantity);
            productLocalisationId = itemView.findViewById(R.id.prodLocId);
            updateProductLocalisation = itemView.findViewById(R.id.updateProductLocalisation);

            updateProductLocalisation.setOnClickListener( v -> {
                String productIndexCleanText = productIndex.getText().toString().replace("Index Produktu: ", "");
                String localisationIndexCleanText = localisationIndex.getText().toString().replace("Index Lokalizacji: ", "");
                String productLocalisationIdCleanText = productLocalisationId.getText().toString().replace("Numer Encji: ", "");

                ProductLocalisation productLocalisation = new ProductLocalisation();
                productLocalisation.setId(Long.valueOf(productLocalisationIdCleanText));
                productLocalisation.setProductId(productId);
                productLocalisation.setLocalisationId(localisationId);
                productLocalisation.setLocalisationIndex(localisationIndexCleanText);
                productLocalisation.setProductIndex(productIndexCleanText);
                productLocalisation.setQuantity(new BigDecimal(quantity.getText().toString()));

                productLocalisationListFragment.getPresenter().updateProductLocalisation(productLocalisation);

                ((NavigationHost)productLocalisationListFragment.getActivity())
                        .navigateTo(new ProductLocalisationListFragment(), false);
            });
        }
    }
}
