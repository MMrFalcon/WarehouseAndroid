package com.falcon.warehouse.model;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.ILocalisationContract;
import com.falcon.warehouse.entity.Localisation;
import com.falcon.warehouse.repository.LocalisationRepository;

public class LocalisationModel implements ILocalisationContract.Model {

    private final LocalisationRepository localisationRepository;

    public LocalisationModel(LocalisationRepository localisationRepository) {
        this.localisationRepository = localisationRepository;
    }


    @Override
    public LiveData<Localisation> getLocalisationByIndex(String localisationIndex) {
        return localisationRepository.getLocalisationByIndex(localisationIndex);
    }
}
