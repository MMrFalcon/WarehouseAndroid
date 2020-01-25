package com.falcon.warehouse.presenter;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.BasePresenterImpl;
import com.falcon.warehouse.contract.IProductLocalisationListContract;
import com.falcon.warehouse.entity.ProductLocalisation;

import java.util.List;

public class ProductLocalisationListPresenter extends BasePresenterImpl<IProductLocalisationListContract.View>
        implements IProductLocalisationListContract.Presenter {

    private final IProductLocalisationListContract.Model model;

    public ProductLocalisationListPresenter(IProductLocalisationListContract.Model model) {
        this.model = model;
    }

    @Override
    public void fillListByProductIndex(String productIndex) {
        LiveData<List<ProductLocalisation>> productLocalisations = model.getByProductIndex(productIndex);
        view.addItems(productLocalisations);
    }

    @Override
    public void fillListByLocalisationIndex(String localisationIndex) {
        LiveData<List<ProductLocalisation>> productLocalisations = model.getByLocalisationIndex(localisationIndex);
        view.addItems(productLocalisations);
    }

    @Override
    public void fillList() {
        LiveData<List<ProductLocalisation>> productLocalisations = model.getAll();
        view.addItems(productLocalisations);
    }
}
