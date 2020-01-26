package com.falcon.warehouse.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.BasePresenterImpl;
import com.falcon.warehouse.contract.ILocalisationAddUpdateContract;
import com.falcon.warehouse.entity.Localisation;

public class LocalisationAddEditPresenter extends BasePresenterImpl<ILocalisationAddUpdateContract.View>
        implements ILocalisationAddUpdateContract.Presenter {

    private final ILocalisationAddUpdateContract.Model model;
    private LifecycleOwner lifecycleOwner;

    public LocalisationAddEditPresenter(ILocalisationAddUpdateContract.Model model) {
        this.model = model;
    }

    @Override
    public void attachLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override
    public void setFormData(String index) {
        LiveData<Localisation> localisationLiveData = model.getByIndex(index);
        localisationLiveData.observe(lifecycleOwner, localisation -> {
            if (localisation != null) {
                view.setIndex(localisation.getLocalisationIndex());
                view.setName(localisation.getLocalisationName());
                view.setLocalisationId(localisation.getId());
            }
        });
    }

    @Override
    public void saveData() {
        Localisation localisation = new Localisation();
        localisation.setLocalisationIndex(view.getIndex());
        localisation.setLocalisationName(view.getName());
        model.addLocalisation(localisation);
    }

    @Override
    public void updateData() {
        Localisation localisation = new Localisation();
        localisation.setLocalisationName(view.getName());
        localisation.setLocalisationIndex(view.getIndex());
        localisation.setId(view.getLocalisationId());
        model.updateLocalisation(localisation);
    }
}
