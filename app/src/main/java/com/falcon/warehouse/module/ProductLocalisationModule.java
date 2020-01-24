package com.falcon.warehouse.module;

import com.falcon.warehouse.contract.IProductLocalisationScannerContract;
import com.falcon.warehouse.model.ProductLocalisationScannerModel;
import com.falcon.warehouse.presenter.ProductLocalisationPresenter;
import com.falcon.warehouse.repository.ProductLocalisationRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductLocalisationModule {

    @Singleton
    @Provides
    public IProductLocalisationScannerContract.Model provideScannerModel(
            ProductLocalisationRepository productLocalisationRepository) {

        return new ProductLocalisationScannerModel(productLocalisationRepository);
    }

    @Singleton
    @Provides
    public IProductLocalisationScannerContract.Presenter provideScannerPresenter(
            IProductLocalisationScannerContract.Model model) {
        return new ProductLocalisationPresenter(model);
    }
}
