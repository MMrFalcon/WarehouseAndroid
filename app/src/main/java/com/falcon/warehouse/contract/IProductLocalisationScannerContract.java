package com.falcon.warehouse.contract;

public interface IProductLocalisationScannerContract {

    interface Model {
        void updateByProductIndex(String productIndex);
        void updateByLocalisationIndex(String localisationIndex);
    }

    interface View {
        String getProductIndex();
        void setProductIndex(String productIndex);
        String getLocalisationIndex();
        void setLocalisationIndex(String localisationIndex);
    }

    interface Presenter extends BasePresenter<View> {
        void fetchByProductIndex();
        void fetchByLocalisationIndex();
    }
}
