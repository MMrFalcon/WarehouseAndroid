package com.falcon.warehouse.contract;


import androidx.lifecycle.LiveData;

import com.falcon.warehouse.entity.Localisation;

public interface ILocalisationContract {

    interface Model {
        LiveData<Localisation> getLocalisationByIndex(String localisationIndex);
    }

    interface View {
        void setLocalisation(LiveData<Localisation> localisation);
        void setLocalisationId(String localisationId);
        void setLocalisationIndex(String localisationIndex);
        void setLocalisationName(String localisationName);
    }

    //todo add detach view method and add base Presenter interface for this methods
    interface Presenter {
        void setView(View view);
        void setLocalisationToTextView();
    }
}
