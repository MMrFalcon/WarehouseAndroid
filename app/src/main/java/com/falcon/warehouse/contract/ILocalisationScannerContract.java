package com.falcon.warehouse.contract;

public interface ILocalisationScannerContract {

    interface Model {
        void updateLocalisationByIndex(String localisationIndex);
    }

    interface View {
        String getLocalisationIndex();
        void setLocalisationIndex(String localisationIndex);
    }

    interface Presenter extends BasePresenter<View> {
        void fetchLocalisationByIndex();
    }
}
