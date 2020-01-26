package com.falcon.warehouse.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.falcon.warehouse.NavigationHost;
import com.falcon.warehouse.R;
import com.falcon.warehouse.contract.IProductAddUpdateContract;
import com.falcon.warehouse.root.App;
import com.falcon.warehouse.root.Constants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

import javax.inject.Inject;

public class ProductAddEditFragment extends Fragment implements IProductAddUpdateContract.View {

    private TextInputEditText index;
    private TextInputEditText name;
    private TextInputEditText quantity;
    private MaterialTextView id;
    private MaterialButton save;

    @Inject
    IProductAddUpdateContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((App) this.getActivity().getApplication()).getComponent().inject(this);

        View fragmentView = inflater.inflate(R.layout.insert_edit_product_card, container, false);

        presenter.attachView(this);
        presenter.attachLifecycleOwner(this);

        index = fragmentView.findViewById(R.id.editProductIndex);
        name = fragmentView.findViewById(R.id.editProductName);
        quantity = fragmentView.findViewById(R.id.editProductQuantity);
        save = fragmentView.findViewById(R.id.saveProduct);
        id = fragmentView.findViewById(R.id.productId);

        save.setOnClickListener(v -> {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                if (Objects.equals(bundle.getString(Constants.INPUT_TYPE), Constants.INSERT)) {
                    presenter.saveData();
                } else {
                    presenter.updateData();
                }
            }

            //redirect to product detail
            Bundle bundleForRedirect= new Bundle();
            bundleForRedirect.putString(Constants.SCAN_PRODUCT_KEY, getIndex());
            ProductDetailFragment productDetailFragment = new ProductDetailFragment();
            productDetailFragment.setArguments(bundleForRedirect);

            ((NavigationHost) getActivity()).navigateTo(productDetailFragment, false);
        });

        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.attachLifecycleOwner(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView(this);
    }

    @Override
    public void setName(String localisationName) {
        name.setText(localisationName);
    }

    @Override
    public void setIndex(String localisationIndex) {
        index.setText(localisationIndex);
    }

    @Override
    public void setQuantity(BigDecimal quantity) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        this.quantity.setText(df.format(quantity));
    }

    @Override
    public void setProductId(Long id) {
        this.id.setText(String.valueOf(id));
    }

    @Override
    public Long getProductId() {
        return Long.valueOf(this.id.getText().toString());
    }

    @Override
    public String getName() {
        return Objects.requireNonNull(this.name.getText()).toString();
    }

    @Override
    public String getIndex() {
        return Objects.requireNonNull(this.index.getText()).toString();
    }

    @Override
    public BigDecimal getQuantity() {
        return new BigDecimal(Objects.requireNonNull(this.quantity.getText()).toString());
    }
}
