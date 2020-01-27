package com.falcon.warehouse.contract;

import java.math.BigDecimal;

public interface IProductScannerContract {

    interface Model {
        void updateProductByIndex(String productIndex);
        void addLocalisationToProduct(String productIndex, String localisationIndex, BigDecimal quantity);
    }

    interface View {
        String getProductIndex();
        void setProductIndex(String productIndex);
    }

    interface Presenter extends BasePresenter<View> {
        void fetchProductByIndex();
        void addLocalisationToProduct(String localisationIndex, BigDecimal quantity);
    }
}
