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

public class ProductLocalisationFragment extends Fragment {

    private final String PAGE_TITLE = "Szczegóły Menu";

    private MaterialButton scanLocalisationButton;
    private MaterialButton scanProductButton;
    private MaterialButton showAllButton;
    private MaterialButton addButton;
    private MaterialTextView menuTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((App) this.getActivity().getApplication()).getComponent().inject(this);

        View fragmentView = inflater.inflate(R.layout.product_localisation_fragment, container, false);

        scanLocalisationButton = fragmentView.findViewById(R.id.scanLocalisationButton);
        scanProductButton = fragmentView.findViewById(R.id.scanProductButton);
        showAllButton = fragmentView.findViewById(R.id.showAllButton);
        addButton = fragmentView.findViewById(R.id.addButton);
        menuTitle = fragmentView.findViewById(R.id.menuTitle);


        menuTitle.setText(PAGE_TITLE);

        scanLocalisationButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.SCAN_TYPE_KEY, Constants.SCAN_LOCALISATION_KEY);

            Fragment scannerFragment = new ProductLocalisationScannerFragment();
            scannerFragment.setArguments(bundle);
            ((NavigationHost) getActivity()).navigateTo(scannerFragment, true);
        });

        scanProductButton.setOnClickListener( v -> {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.SCAN_TYPE_KEY, Constants.SCAN_PRODUCT_KEY);

            Fragment scannerFragment = new ProductLocalisationScannerFragment();
            scannerFragment.setArguments(bundle);
            ((NavigationHost) getActivity()).navigateTo(scannerFragment, true);
        });

        showAllButton.setOnClickListener( v -> ((NavigationHost) getActivity())
                .navigateTo(new ProductLocalisationListFragment(), true));

        return fragmentView;
    }
}
