package com.falcon.warehouse.model;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.ILocalisationListContract;
import com.falcon.warehouse.entity.Localisation;
import com.falcon.warehouse.repository.LocalisationRepository;

import java.util.List;

public class LocalisationListModel implements ILocalisationListContract.Model {

    private final LocalisationRepository localisationRepository;

    public LocalisationListModel(LocalisationRepository localisationRepository) {
        this.localisationRepository = localisationRepository;
    }

    @Override
    public LiveData<List<Localisation>> getAll() {
        return localisationRepository.getAll();
    }

    @Override
    public void deleteLocalisation(Localisation localisation) {
        localisationRepository.deleteLocalisation(localisation);
    }
}
