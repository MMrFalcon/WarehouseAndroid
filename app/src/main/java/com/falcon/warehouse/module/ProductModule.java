package com.falcon.warehouse.module;

import com.falcon.warehouse.contract.IProductDetailContract;
import com.falcon.warehouse.contract.IProductListContract;
import com.falcon.warehouse.contract.IProductScannerContract;
import com.falcon.warehouse.model.ProductDetailModel;
import com.falcon.warehouse.model.ProductListModel;
import com.falcon.warehouse.model.ProductScannerModel;
import com.falcon.warehouse.presenter.ProductDetailPresenter;
import com.falcon.warehouse.presenter.ProductListPresenter;
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

    @Singleton
    @Provides
    public IProductDetailContract.Presenter provideDetailPresenter(IProductDetailContract.Model model) {
        return new ProductDetailPresenter(model);
    }

    @Singleton
    @Provides
    public IProductDetailContract.Model provideDetailModel(ProductRepository productRepository) {
        return new ProductDetailModel(productRepository);
    }

    @Singleton
    @Provides
    public IProductListContract.Presenter provideListPresenter(IProductListContract.Model model) {
        return new ProductListPresenter(model);
    }

    @Singleton
    @Provides
    public IProductListContract.Model provideListModel(ProductRepository productRepository) {
        return new ProductListModel(productRepository);
    }
}
