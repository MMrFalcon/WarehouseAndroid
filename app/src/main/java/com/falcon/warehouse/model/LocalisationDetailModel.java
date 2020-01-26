package com.falcon.warehouse.model;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.ILocalisationDetailContract;
import com.falcon.warehouse.entity.Localisation;
import com.falcon.warehouse.repository.LocalisationRepository;

public class LocalisationDetailModel implements ILocalisationDetailContract.Model {

    private final LocalisationRepository localisationRepository;

    public LocalisationDetailModel(LocalisationRepository localisationRepository) {
        this.localisationRepository = localisationRepository;
    }


    @Override
    public LiveData<Localisation> getLocalisationByIndex(String localisationIndex) {
        return localisationRepository.getLocalisationByIndex(localisationIndex);
    }

    @Override
    public void deleteLocalisation(Localisation localisation) {
        localisationRepository.deleteLocalisation(localisation);
    }
}
