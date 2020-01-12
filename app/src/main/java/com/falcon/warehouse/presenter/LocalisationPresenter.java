package com.falcon.warehouse.presenter;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.BasePresenterImpl;
import com.falcon.warehouse.contract.ILocalisationContract;
import com.falcon.warehouse.entity.Localisation;

public class LocalisationPresenter extends BasePresenterImpl<ILocalisationContract.View> implements ILocalisationContract.Presenter {

    private final ILocalisationContract.Model model;

    public LocalisationPresenter(ILocalisationContract.Model model) {
        this.model = model;
    }

    @Override
    public void setLocalisationToTextView() {
        LiveData<Localisation> localisationLiveData = model.getLocalisationByIndex("M01R01P03V");
        view.setLocalisation(localisationLiveData);
    }
}
