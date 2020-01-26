package com.falcon.warehouse.presenter;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.BasePresenterImpl;
import com.falcon.warehouse.contract.IProductDetailContract;
import com.falcon.warehouse.entity.Product;

public class ProductDetailPresenter extends BasePresenterImpl<IProductDetailContract.View>
        implements IProductDetailContract.Presenter {

    private final IProductDetailContract.Model model;

    public ProductDetailPresenter(IProductDetailContract.Model model) {
        this.model = model;
    }

    @Override
    public void setProductToTextView(String productIndex) {
        LiveData<Product> productLiveData = model.getProductByIndex(productIndex);
        view.setProduct(productLiveData);
    }

    @Override
    public void delete() {
        Product product = new Product();
        product.setId(view.getProductId());
        product.setQuantity(view.getProductQuantity());
        product.setProductName(view.getProductName());
        product.setProductIndex(view.getProductIndex());

        model.deleteProduct(product);
    }
}
