package com.falcon.warehouse.fragment;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import com.falcon.warehouse.NavigationHost;
import com.falcon.warehouse.R;
import com.falcon.warehouse.contract.IProductDetailContract;
import com.falcon.warehouse.entity.Product;
import com.falcon.warehouse.root.App;
import com.falcon.warehouse.root.Constants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.math.BigDecimal;
import java.util.Objects;

import javax.inject.Inject;

public class ProductDetailFragment extends Fragment implements IProductDetailContract.View {

    private MaterialTextView productId;
    private MaterialTextView productIndex;
    private MaterialTextView productName;
    private MaterialTextView productQuantity;
    private MaterialButton edit;
    private MaterialButton delete;
    private MaterialButton addLocalisation;

    @Inject
    IProductDetailContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((App) this.getActivity().getApplication()).getComponent().inject(this);
        View fragmentView = inflater.inflate(R.layout.product_detail_fragment, container, false);

        presenter.attachView(this);

        productId = fragmentView.findViewById(R.id.productId);
        productIndex = fragmentView.findViewById(R.id.productIndex);
        productName = fragmentView.findViewById(R.id.productName);
        productQuantity = fragmentView.findViewById(R.id.productQuantity);
        edit = fragmentView.findViewById(R.id.editProd);
        delete = fragmentView.findViewById(R.id.removeProd);
        addLocalisation =  fragmentView.findViewById(R.id.addLocToProd);

        //update by index or fetch last scanned data
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            presenter.setProductToTextView(bundle.getString(Constants.SCAN_PRODUCT_KEY));
        } else {
            presenter.setProductToTextView("");
        }

        edit.setOnClickListener(v -> {
            Bundle bundleForUpdate = new Bundle();
            String productIndexCleanText = productIndex.getText().toString().replace("Index: ", "");
            bundleForUpdate.putString(Constants.SCAN_PRODUCT_KEY, productIndexCleanText);
            bundleForUpdate.putString(Constants.INPUT_TYPE, Constants.UPDATE);

            ProductAddEditFragment productAddEditFragment = new ProductAddEditFragment();
            productAddEditFragment.setArguments(bundleForUpdate);

            ((NavigationHost) getActivity()).navigateTo(productAddEditFragment, true);
        });

        delete.setOnClickListener(v -> {
            presenter.delete();

            ((NavigationHost) getActivity()).navigateTo(new ProductListFragment(), false);
        });

        addLocalisation.setOnClickListener(v -> showPopupWindow());

        return fragmentView;
    }

    private void showPopupWindow() {
        final TextInputEditText quantity = new TextInputEditText(this.getContext());
        quantity.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this.getContext());
        alertDialogBuilder.setView(quantity);
        alertDialogBuilder.setTitle("Podaj Ilość");
        alertDialogBuilder.setPositiveButton("Skanuj Lokalizacje", (dialog, which) -> {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.SCAN_TYPE_KEY, Constants.ADD_LOCALISATION_TO_PRODUCT);
            bundle.putString(Constants.QUANTITY, Objects.requireNonNull(quantity.getText()).toString());
            bundle.putString(Constants.PROD_PRODUCT_INDEX_KEY, getProductIndex());

            LocalisationScannerFragment localisationScannerFragment = new LocalisationScannerFragment();
            localisationScannerFragment.setArguments(bundle);

            ((NavigationHost)getActivity()).navigateTo(localisationScannerFragment, false);
        });

        alertDialogBuilder.setNegativeButton("Wróć", ((dialog, which) -> {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.SCAN_PRODUCT_KEY, getProductIndex());

            ProductDetailFragment productDetailFragment = new ProductDetailFragment();
            productDetailFragment.setArguments(bundle);

            ((NavigationHost)getActivity()).navigateTo(productDetailFragment, true);
        }));

        alertDialogBuilder.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
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
    public void setProduct(LiveData<Product> product) {
        product.observe(this, product1 -> {
            if (product1 != null) {
                setProductId(product1.getId());
                setProductIndex(product1.getProductIndex());
                setProductName(product1.getProductName());
                setQuantity(product1.getQuantity());
            }
        });
    }

    @Override
    public void setProductId(Long productId) {
        this.productId.setText("ID: " + productId);
    }

    @Override
    public void setProductIndex(String productIndex) {
        this.productIndex.setText("Index: " + productIndex);
    }

    @Override
    public void setProductName(String productName) {
        this.productName.setText("Nazwa: " + productName);
    }

    @Override
    public void setQuantity(BigDecimal quantity) {
        this.productQuantity.setText("Ilość: " + quantity.toString());
    }

    @Override
    public Long getProductId() {
        return Long.valueOf(productId.getText().toString().replace("ID: ", ""));
    }

    @Override
    public String getProductIndex() {
        return productIndex.getText().toString().replace("Index: ", "");
    }

    @Override
    public String getProductName() {
        return productName.getText().toString().replace("Nazwa: ", "");
    }

    @Override
    public BigDecimal getProductQuantity() {
        return new BigDecimal(productQuantity.getText().toString().replace("Ilość: ", ""));
    }
}
