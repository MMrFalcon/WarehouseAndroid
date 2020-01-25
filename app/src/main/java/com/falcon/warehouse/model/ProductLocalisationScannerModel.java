package com.falcon.warehouse.model;

import android.util.Log;

import com.falcon.warehouse.contract.IProductLocalisationScannerContract;
import com.falcon.warehouse.repository.ProductLocalisationRepository;

public class ProductLocalisationScannerModel implements IProductLocalisationScannerContract.Model {

    private final ProductLocalisationRepository productLocalisationRepository;

    public ProductLocalisationScannerModel(ProductLocalisationRepository productLocalisationRepository) {
        this.productLocalisationRepository = productLocalisationRepository;
    }

    @Override
    public void updateByProductIndex(String productIndex) {
        Log.i("UPDATING ", "UPDATING BY PROD INDEX" + productIndex);
        this.productLocalisationRepository.getByProductIndex(productIndex);
    }

    @Override
    public void updateByLocalisationIndex(String localisationIndex) {
        this.productLocalisationRepository.getByLocalisationIndex(localisationIndex);
    }
}
