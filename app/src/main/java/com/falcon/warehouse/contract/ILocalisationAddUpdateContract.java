package com.falcon.warehouse.contract;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.falcon.warehouse.entity.Localisation;

public interface ILocalisationAddUpdateContract {

    interface Model {
        void addLocalisation(Localisation localisation);
        void updateLocalisation(Localisation localisation);
        LiveData<Localisation> getByIndex(String index);
    }

    interface View {
        void setName(String localisationName);
        void setIndex(String localisationIndex);
        void setLocalisationId(Long id);
        Long getLocalisationId();
        String getName();
        String getIndex();
    }

    interface Presenter extends BasePresenter<View> {
        void attachLifecycleOwner(LifecycleOwner lifecycleOwner);
        void setFormData(String index);
        void saveData();
        void updateData();
    }
}
