package com.falcon.warehouse.contract;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.entity.Product;

import java.math.BigDecimal;

public interface IProductDetailContract {

    interface Model {
        LiveData<Product> getProductByIndex(String productIndex);
    }

    interface View {
        void setProduct(LiveData<Product> product);
        void setProductId(Long productId);
        void setProductIndex(String productIndex);
        void setProductName(String productName);
        void setQuantity(BigDecimal quantity);
    }

    interface Presenter extends BasePresenter<View> {
        void setProductToTextView(String productIndex);
    }
}
