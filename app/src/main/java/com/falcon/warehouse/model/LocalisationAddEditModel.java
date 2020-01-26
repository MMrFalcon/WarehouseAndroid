package com.falcon.warehouse.model;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.ILocalisationAddUpdateContract;
import com.falcon.warehouse.entity.Localisation;
import com.falcon.warehouse.repository.LocalisationRepository;

public class LocalisationAddEditModel implements ILocalisationAddUpdateContract.Model {

    private final LocalisationRepository localisationRepository;

    public LocalisationAddEditModel(LocalisationRepository localisationRepository) {
        this.localisationRepository = localisationRepository;
    }

    @Override
    public void addLocalisation(Localisation localisation) {
        localisationRepository.saveNewLocalisation(localisation);
    }

    @Override
    public void updateLocalisation(Localisation localisation) {
        localisationRepository.updateLocalisation(localisation);
    }

    @Override
    public LiveData<Localisation> getByIndex(String index) {
        return localisationRepository.getLocalisationByIndex(index);
    }
}
