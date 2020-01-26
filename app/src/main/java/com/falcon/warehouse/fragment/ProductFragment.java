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
import com.falcon.warehouse.root.App;
import com.falcon.warehouse.root.Constants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class ProductFragment extends Fragment {
    private final String PAGE_TITLE = "Produkt Menu";

    private MaterialButton scanButton;
    private MaterialButton showAllButton;
    private MaterialButton addButton;
    private MaterialTextView menuTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((App) this.getActivity().getApplication()).getComponent().inject(this);
        View view = inflater.inflate(R.layout.product_fragment, container, false);

        scanButton = view.findViewById(R.id.scanButton);
        showAllButton = view.findViewById(R.id.showAllButton);
        addButton = view.findViewById(R.id.addButton);
        menuTitle = view.findViewById(R.id.menuTitle);

        menuTitle.setText(PAGE_TITLE);

        scanButton.setOnClickListener( v -> ((NavigationHost) getActivity())
                .navigateTo(new ProductScannerFragment(), true));

        showAllButton.setOnClickListener(v -> ((NavigationHost) getActivity())
                .navigateTo(new ProductListFragment(), true));

        Bundle bundle = new Bundle();
        bundle.putString(Constants.INPUT_TYPE, Constants.INSERT);
        ProductAddEditFragment productAddEditFragment = new ProductAddEditFragment();
        productAddEditFragment.setArguments(bundle);

        addButton.setOnClickListener(v -> ((NavigationHost) getActivity()).navigateTo(productAddEditFragment, true));

        return view;
    }
}
