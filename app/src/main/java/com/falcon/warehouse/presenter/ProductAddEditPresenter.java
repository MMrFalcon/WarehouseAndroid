package com.falcon.warehouse.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.BasePresenterImpl;
import com.falcon.warehouse.contract.IProductAddUpdateContract;
import com.falcon.warehouse.entity.Product;


public class ProductAddEditPresenter extends BasePresenterImpl<IProductAddUpdateContract.View>
        implements IProductAddUpdateContract.Presenter {

    private final IProductAddUpdateContract.Model model;
    private LifecycleOwner lifecycleOwner;

    public ProductAddEditPresenter(IProductAddUpdateContract.Model model) {
        this.model = model;
    }

    @Override
    public void attachLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override
    public void setFormData(String index) {
        LiveData<Product> productLiveData = model.getByIndex(index);

        productLiveData.observe(lifecycleOwner, product -> {
            if (product != null) {
                view.setIndex(product.getProductIndex());
                view.setName(product.getProductName());
                view.setQuantity(product.getQuantity());
                view.setProductId(product.getId());
            }
        });
    }

    @Override
    public void saveData() {
        Product product = new Product();
        product.setProductIndex(view.getIndex());
        product.setProductName(view.getName());
        product.setQuantity(view.getQuantity());

        model.addProduct(product);
    }

    @Override
    public void updateData() {
        Product product = new Product();
        product.setProductIndex(view.getIndex());
        product.setProductName(view.getName());
        product.setQuantity(view.getQuantity());
        product.setId(view.getProductId());

        model.updateProduct(product);
    }
}
