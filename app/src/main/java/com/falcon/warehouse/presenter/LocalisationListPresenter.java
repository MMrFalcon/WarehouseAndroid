package com.falcon.warehouse.presenter;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.BasePresenterImpl;
import com.falcon.warehouse.contract.ILocalisationListContract;
import com.falcon.warehouse.entity.Localisation;

import java.util.List;

public class LocalisationListPresenter extends BasePresenterImpl<ILocalisationListContract.View>
        implements ILocalisationListContract.Presenter {

    private final ILocalisationListContract.Model model;

    public LocalisationListPresenter(ILocalisationListContract.Model model) {
        this.model = model;
    }

    @Override
    public void fillList() {
        LiveData<List<Localisation>> localisations = model.getAll();
        view.addItems(localisations);
    }
}
