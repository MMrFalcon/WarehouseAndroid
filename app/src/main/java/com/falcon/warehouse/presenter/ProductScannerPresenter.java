package com.falcon.warehouse.presenter;

import com.falcon.warehouse.contract.BasePresenterImpl;
import com.falcon.warehouse.contract.IProductScannerContract;

public class ProductScannerPresenter extends BasePresenterImpl<IProductScannerContract.View> implements IProductScannerContract.Presenter {

    private final IProductScannerContract.Model model;

    public ProductScannerPresenter(IProductScannerContract.Model model) {
        this.model = model;
    }

    @Override
    public void fetchProductByIndex() {
        final String productIndex = view.getProductIndex();
        model.updateProductByIndex(productIndex);
    }
}
