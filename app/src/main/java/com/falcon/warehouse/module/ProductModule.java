package com.falcon.warehouse.module;

import com.falcon.warehouse.contract.IProductScannerContract;
import com.falcon.warehouse.model.ProductScannerModel;
import com.falcon.warehouse.presenter.ProductScannerPresenter;
import com.falcon.warehouse.repository.ProductRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductModule {

    @Singleton
    @Provides
    public IProductScannerContract.Presenter provideScannerPresenter(IProductScannerContract.Model model) {
        return new ProductScannerPresenter(model);
    }

    @Singleton
    @Provides
    public IProductScannerContract.Model provideScannerModel(ProductRepository productRepository) {
        return new ProductScannerModel(productRepository);
    }
}
