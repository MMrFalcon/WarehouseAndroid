package com.falcon.warehouse.contract;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.entity.Product;

import java.math.BigDecimal;

public interface IProductDetailContract {

    interface Model {
        LiveData<Product> getProductByIndex(String productIndex);
        void deleteProduct(Product product);
    }

    interface View {
        void setProduct(LiveData<Product> product);
        void setProductId(Long productId);
        void setProductIndex(String productIndex);
        void setProductName(String productName);
        void setQuantity(BigDecimal quantity);
        Long getProductId();
        String getProductIndex();
        String getProductName();
        BigDecimal getProductQuantity();
    }

    interface Presenter extends BasePresenter<View> {
        void setProductToTextView(String productIndex);
        void delete();
    }
}
