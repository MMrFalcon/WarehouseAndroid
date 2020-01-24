package com.falcon.warehouse.presenter;

import com.falcon.warehouse.contract.BasePresenterImpl;
import com.falcon.warehouse.contract.IProductLocalisationScannerContract;

public class ProductLocalisationPresenter extends BasePresenterImpl<IProductLocalisationScannerContract.View>
        implements IProductLocalisationScannerContract.Presenter {

    private final IProductLocalisationScannerContract.Model model;

    public ProductLocalisationPresenter(IProductLocalisationScannerContract.Model model) {
        this.model = model;
    }

    @Override
    public void fetchByProductIndex() {
        final String productIndex = view.getProductIndex();
        model.updateByProductIndex(productIndex);
    }

    @Override
    public void fetchByLocalisationIndex() {
        final String localisationIndex = view.getLocalisationIndex();
        model.updateByLocalisationIndex(localisationIndex);
    }
}
