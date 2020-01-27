package com.falcon.warehouse.contract;

import java.math.BigDecimal;

public interface ILocalisationScannerContract {

    interface Model {
        void updateLocalisationByIndex(String localisationIndex);
        void addProductToLocalisation(String localisationIndex, String productIndex, BigDecimal quantity);
    }

    interface View {
        String getLocalisationIndex();
        void setLocalisationIndex(String localisationIndex);
    }

    interface Presenter extends BasePresenter<View> {
        void fetchLocalisationByIndex();
        void addProductToLocalisation(String productIndex, BigDecimal quantity);
    }
}
