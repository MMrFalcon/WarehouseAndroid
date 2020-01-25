package com.falcon.warehouse.contract;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.entity.Localisation;

public interface ILocalisationDetailContract {
    interface Model {
        LiveData<Localisation> getLocalisationByIndex(String localisationIndex);
    }

    interface View {
        void setLocalisation(LiveData<Localisation> localisation);
        void setLocalisationId(String localisationId);
        void setLocalisationIndex(String localisationIndex);
        void setLocalisationName(String localisationName);
    }

    interface Presenter extends BasePresenter<ILocalisationDetailContract.View> {
        void setLocalisationToTextView(String localisationIndex);
    }
}
