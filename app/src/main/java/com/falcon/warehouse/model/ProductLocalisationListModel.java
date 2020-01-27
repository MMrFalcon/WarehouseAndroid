package com.falcon.warehouse.model;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.IProductLocalisationListContract;
import com.falcon.warehouse.entity.ProductLocalisation;
import com.falcon.warehouse.repository.ProductLocalisationRepository;

import java.util.List;

public class ProductLocalisationListModel implements IProductLocalisationListContract.Model {

    private final ProductLocalisationRepository productLocalisationRepository;

    public ProductLocalisationListModel(ProductLocalisationRepository productLocalisationRepository) {
        this.productLocalisationRepository = productLocalisationRepository;
    }

    @Override
    public LiveData<List<ProductLocalisation>> getByProductIndex(String productIndex) {
        return productLocalisationRepository.getByProductIndex(productIndex);
    }

    @Override
    public LiveData<List<ProductLocalisation>> getByLocalisationIndex(String localisationIndex) {
        return productLocalisationRepository.getByLocalisationIndex(localisationIndex);
    }

    @Override
    public LiveData<List<ProductLocalisation>> getAll() {
        return productLocalisationRepository.getAll();
    }

    @Override
    public void updateProductLocalisation(ProductLocalisation productLocalisation) {
        productLocalisationRepository.updateProductLocalisation(productLocalisation);
    }
}
