package com.falcon.warehouse.contract;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.entity.Localisation;

public interface ILocalisationDetailContract {
    interface Model {
        LiveData<Localisation> getLocalisationByIndex(String localisationIndex);
        void deleteLocalisation(Localisation localisation);
    }

    interface View {
        void setLocalisation(LiveData<Localisation> localisation);
        void setLocalisationId(String localisationId);
        void setLocalisationIndex(String localisationIndex);
        void setLocalisationName(String localisationName);
        Long getLocalisationId();
        String getIndex();
        String getName();
    }

    interface Presenter extends BasePresenter<ILocalisationDetailContract.View> {
        void setLocalisationToTextView(String localisationIndex);
        void delete();
    }
}
