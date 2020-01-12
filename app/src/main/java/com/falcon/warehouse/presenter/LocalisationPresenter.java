package com.falcon.warehouse.presenter;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.ILocalisationContract;
import com.falcon.warehouse.entity.Localisation;

public class LocalisationPresenter implements ILocalisationContract.Presenter {

    private final ILocalisationContract.Model model;
    private ILocalisationContract.View view;

    public LocalisationPresenter(ILocalisationContract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(ILocalisationContract.View view) {
        this.view = view;
    }

    @Override
    public void setLocalisationToTextView() {
        LiveData<Localisation> localisationLiveData = model.getLocalisationByIndex("M01R01P03V");
        view.setLocalisation(localisationLiveData);
    }
}
