package com.falcon.warehouse.contract;

public interface IProductScannerContract {

    interface Model {
        void updateProductByIndex(String productIndex);
    }

    interface View {
        String getProductIndex();
        void setProductIndex(String productIndex);
    }

    interface Presenter extends BasePresenter<View> {
        void fetchProductByIndex();
    }
}
