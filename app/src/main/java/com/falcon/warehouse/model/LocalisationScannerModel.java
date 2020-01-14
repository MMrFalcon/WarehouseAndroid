package com.falcon.warehouse.model;

import com.falcon.warehouse.contract.ILocalisationScannerContract;
import com.falcon.warehouse.repository.LocalisationRepository;

public class LocalisationScannerModel implements ILocalisationScannerContract.Model {

    private final LocalisationRepository localisationRepository;

    public LocalisationScannerModel(LocalisationRepository localisationRepository) {
        this.localisationRepository = localisationRepository;
    }

    @Override
    public void updateLocalisationByIndex(String localisationIndex) {
        localisationRepository.getLocalisationByIndex(localisationIndex);
    }
}
