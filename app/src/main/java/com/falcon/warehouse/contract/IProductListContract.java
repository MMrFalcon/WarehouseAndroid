package com.falcon.warehouse.contract;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.entity.Product;

import java.util.List;

public interface IProductListContract {

    interface Model {
        LiveData<List<Product>> getAll();
        void deleteProduct(Product product);
    }

    interface View {
        void addItems(LiveData<List<Product>> products);
    }

    interface Presenter extends BasePresenter<View> {
        void fillList();
        void removeProduct(Product product);
    }
}
