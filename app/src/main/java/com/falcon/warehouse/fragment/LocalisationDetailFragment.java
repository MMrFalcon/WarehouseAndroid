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
import com.falcon.warehouse.contract.ILocalisationDetailContract;
import com.falcon.warehouse.entity.Localisation;
import com.falcon.warehouse.root.App;
import com.falcon.warehouse.root.Constants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;

import javax.inject.Inject;

public class LocalisationDetailFragment extends Fragment implements ILocalisationDetailContract.View {

    @Inject
    ILocalisationDetailContract.Presenter presenter;

    private MaterialTextView localisationId;
    private MaterialTextView localisationName;
    private MaterialTextView localisationIndex;
    private MaterialButton edit;
    private MaterialButton delete;
    private MaterialButton addProdToLoc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((App) this.getActivity().getApplication()).getComponent().inject(this);
        View fragmentView = inflater.inflate(R.layout.localisation_detail_fragment, container, false);

        localisationId = fragmentView.findViewById(R.id.localisationDetailId);
        localisationName = fragmentView.findViewById(R.id.localisationDetailName);
        localisationIndex = fragmentView.findViewById(R.id.localisationDetailIndex);
        edit = fragmentView.findViewById(R.id.editLoc);
        delete = fragmentView.findViewById(R.id.removeLoc);
        addProdToLoc = fragmentView.findViewById(R.id.addProdToLoc);

        presenter.attachView(this);

        Bundle bundle = this.getArguments();

        //update by index or fetch last scanned data
        if (bundle != null) {
            presenter.setLocalisationToTextView(bundle.getString(Constants.SCAN_LOCALISATION_KEY));
        } else {
            presenter.setLocalisationToTextView("");
        }

        edit.setOnClickListener(v -> {

            Bundle bundleForUpdate = new Bundle();
            String localisationIndexCleanText = localisationIndex.getText().toString().replace("Index: ", "");
            bundleForUpdate.putString(Constants.SCAN_LOCALISATION_KEY, localisationIndexCleanText);
            bundleForUpdate.putString(Constants.INPUT_TYPE, Constants.UPDATE);

            LocalisationAddEditFragment localisationAddEditFragment = new LocalisationAddEditFragment();
            localisationAddEditFragment.setArguments(bundleForUpdate);

            ((NavigationHost) getActivity()).navigateTo(localisationAddEditFragment, true);
        });

        delete.setOnClickListener( v -> {
            presenter.delete();

            ((NavigationHost) getActivity()).navigateTo(new LocalisationListFragment(), true);
        });

        addProdToLoc.setOnClickListener(v -> showPopupWindow());
        return fragmentView;
    }

    private void showPopupWindow() {
        final TextInputEditText quantity = new TextInputEditText(this.getContext());
        quantity.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this.getContext());
        alertDialogBuilder.setView(quantity);
        alertDialogBuilder.setTitle("Podaj Ilość");
        alertDialogBuilder.setPositiveButton("Skanuj Produkt", (dialog, which) -> {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.SCAN_TYPE_KEY, Constants.ADD_PRODUCT_TO_LOCALISATION);
            bundle.putString(Constants.QUANTITY, Objects.requireNonNull(quantity.getText()).toString());
            bundle.putString(Constants.PROD_LOCALISATION_INDEX_KEY, getIndex());

            ProductScannerFragment productScannerFragment = new ProductScannerFragment();
            productScannerFragment.setArguments(bundle);
            ((NavigationHost)getActivity()).navigateTo(productScannerFragment, false);

        });

        alertDialogBuilder.setNegativeButton("Wróć", ((dialog, which) -> {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.SCAN_LOCALISATION_KEY, getIndex());

            LocalisationDetailFragment localisationDetailFragment = new LocalisationDetailFragment();
            localisationDetailFragment.setArguments(bundle);

            ((NavigationHost)getActivity()).navigateTo(localisationDetailFragment, true);
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
    public void setLocalisation(LiveData<Localisation> localisation) {
        localisation.observe(this, localisation1 -> {
            if (localisation1 != null) {
                setLocalisationId(String.valueOf(localisation1.getId()));
                setLocalisationIndex(localisation1.getLocalisationIndex());
                setLocalisationName(localisation1.getLocalisationName());
            }
        });
    }

    @Override
    public void setLocalisationId(String localisationId) {
        this.localisationId.setText("ID: " + localisationId);
    }

    @Override
    public void setLocalisationIndex(String localisationIndex) {
        this.localisationIndex.setText("Index: " + localisationIndex);
    }

    @Override
    public void setLocalisationName(String localisationName) {
        this.localisationName.setText("Nazwa: " + localisationName);
    }

    @Override
    public Long getLocalisationId() {
        String localisationIdCleanText = localisationId.getText().toString().replace("ID: ", "");
        return Long.valueOf(localisationIdCleanText);
    }

    @Override
    public String getIndex() {
        return localisationIndex.getText().toString().replace("Index: ", "");
    }

    @Override
    public String getName() {
        return localisationName.getText().toString().replace("Nazwa: ", "");
    }
}
