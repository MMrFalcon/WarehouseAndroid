package com.falcon.warehouse.presenter;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.BasePresenterImpl;
import com.falcon.warehouse.contract.ILocalisationDetailContract;
import com.falcon.warehouse.entity.Localisation;

public class LocalisationDetailPresenter extends BasePresenterImpl<ILocalisationDetailContract.View> implements ILocalisationDetailContract.Presenter {

    private final ILocalisationDetailContract.Model model;

    public LocalisationDetailPresenter(ILocalisationDetailContract.Model model) {
        this.model = model;
    }

    @Override
    public void setLocalisationToTextView(String localisationIndex) {
        LiveData<Localisation> localisationLiveData = model.getLocalisationByIndex(localisationIndex);
        view.setLocalisation(localisationLiveData);
    }

}
