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
import com.google.android.material.button.MaterialButton;

public class LocalisationFragment extends Fragment {

    private MaterialButton scanLocalisationButton;
    private MaterialButton showAllLocalistionsButton;
    private MaterialButton addLocalisationButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.localisation_fragment, container, false);

        scanLocalisationButton = view.findViewById(R.id.scanLocalisationButton);
        showAllLocalistionsButton = view.findViewById(R.id.showAllLocalisationButton);
        addLocalisationButton = view.findViewById(R.id.addLocalisationButton);

        scanLocalisationButton.setOnClickListener( v -> ((NavigationHost) getActivity())
                .navigateTo(new LocalisationScannerFragment(), true));

        return view;
    }
}
