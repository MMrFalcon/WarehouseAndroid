package com.falcon.warehouse.presenter;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.BasePresenterImpl;
import com.falcon.warehouse.contract.IProductListContract;
import com.falcon.warehouse.entity.Product;

import java.util.List;

public class ProductListPresenter extends BasePresenterImpl<IProductListContract.View>
        implements IProductListContract.Presenter {

    private final IProductListContract.Model model;

    public ProductListPresenter(IProductListContract.Model model) {
        this.model = model;
    }

    @Override
    public void fillList() {
        LiveData<List<Product>> products = model.getAll();
        view.addItems(products);
    }

    @Override
    public void removeProduct(Product product) {
        model.deleteProduct(product);
    }
}
