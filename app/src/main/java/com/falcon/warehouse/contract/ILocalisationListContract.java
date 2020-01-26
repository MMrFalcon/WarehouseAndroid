package com.falcon.warehouse.contract;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.entity.Localisation;

import java.util.List;

public interface ILocalisationListContract {

    interface Model {
        LiveData<List<Localisation>> getAll();
    }

    interface View {
        void addItems(LiveData<List<Localisation>> localisations);
    }

    interface Presenter extends BasePresenter<View> {
        void fillList();
    }

}
