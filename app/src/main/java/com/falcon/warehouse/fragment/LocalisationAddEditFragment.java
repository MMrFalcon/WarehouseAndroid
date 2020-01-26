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
import com.falcon.warehouse.contract.ILocalisationAddUpdateContract;
import com.falcon.warehouse.root.App;
import com.falcon.warehouse.root.Constants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;

import javax.inject.Inject;

public class LocalisationAddEditFragment extends Fragment implements ILocalisationAddUpdateContract.View {

    private TextInputEditText index;
    private TextInputEditText name;
    private MaterialTextView id;
    private MaterialButton save;

    @Inject
    ILocalisationAddUpdateContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((App) this.getActivity().getApplication()).getComponent().inject(this);

        View fragmentView = inflater.inflate(R.layout.insert_edit_localisation_card, container, false);

        presenter.attachView(this);
        presenter.attachLifecycleOwner(this);

        index = fragmentView.findViewById(R.id.editLocalisationIndex);
        name = fragmentView.findViewById(R.id.editLocalisationName);
        save = fragmentView.findViewById(R.id.saveLocalisation);
        id = fragmentView.findViewById(R.id.localisationId);

        save.setOnClickListener(v -> {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                if (Objects.equals(bundle.getString(Constants.INPUT_TYPE), Constants.INSERT)) {
                    presenter.saveData();
                } else {
                    presenter.updateData();
                }
            }
            //redirect to localisation detail
            Bundle bundleForRedirect= new Bundle();
            bundleForRedirect.putString(Constants.SCAN_LOCALISATION_KEY, getIndex());
            LocalisationDetailFragment localisationDetailFragment = new LocalisationDetailFragment();
            localisationDetailFragment.setArguments(bundleForRedirect);

            ((NavigationHost) getActivity()).navigateTo(localisationDetailFragment, false);

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
        this.name.setText(localisationName);
    }

    @Override
    public void setIndex(String localisationIndex) {
        this.index.setText(localisationIndex);
    }

    @Override
    public void setLocalisationId(Long id) {
        this.id.setText(String.valueOf(id));
    }

    @Override
    public Long getLocalisationId() {
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
}
