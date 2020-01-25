package com.falcon.warehouse.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import com.falcon.warehouse.R;
import com.falcon.warehouse.contract.ILocalisationDetailContract;
import com.falcon.warehouse.entity.Localisation;
import com.falcon.warehouse.root.App;
import com.google.android.material.textview.MaterialTextView;

import javax.inject.Inject;

public class LocalisationDetailFragment extends Fragment implements ILocalisationDetailContract.View {

    @Inject
    ILocalisationDetailContract.Presenter presenter;

    private MaterialTextView localisationId;
    private MaterialTextView localisationName;
    private MaterialTextView localisationIndex;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((App) this.getActivity().getApplication()).getComponent().inject(this);
        View fragmentView = inflater.inflate(R.layout.localisation_detail_fragment, container, false);

        localisationId = fragmentView.findViewById(R.id.localisationId);
        localisationName = fragmentView.findViewById(R.id.localisationName);
        localisationIndex = fragmentView.findViewById(R.id.localisationIndex);

        presenter.attachView(this);
        presenter.setLocalisationToTextView();

        return fragmentView;
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
}
