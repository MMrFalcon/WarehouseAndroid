package com.falcon.warehouse.contract;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.entity.ProductLocalisation;

import java.util.List;

public interface IProductLocalisationListContract {

    interface Model {
        LiveData<List<ProductLocalisation>> getByProductIndex(String productIndex);
        LiveData<List<ProductLocalisation>> getByLocalisationIndex(String localisationIndex);
        LiveData<List<ProductLocalisation>> getAll();
        void updateProductLocalisation(ProductLocalisation productLocalisation);
    }

    interface View {
        void addItems(LiveData<List<ProductLocalisation>> items);
    }

    interface Presenter extends BasePresenter<View> {
        void fillListByProductIndex(String productIndex);
        void fillListByLocalisationIndex(String localisationIndex);
        void fillList();
        void updateProductLocalisation(ProductLocalisation productLocalisation);
    }

}
