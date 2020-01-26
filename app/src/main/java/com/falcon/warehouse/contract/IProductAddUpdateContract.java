package com.falcon.warehouse.contract;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.falcon.warehouse.entity.Product;

import java.math.BigDecimal;

public interface IProductAddUpdateContract {

    interface Model {
        void addProduct(Product product);
        void updateProduct(Product product);
        LiveData<Product> getByIndex(String index);
    }

    interface View {
        void setName(String localisationName);
        void setIndex(String localisationIndex);
        void setQuantity(BigDecimal quantity);
        void setProductId(Long id);
        Long getProductId();
        String getName();
        String getIndex();
        BigDecimal getQuantity();
    }

    interface Presenter extends BasePresenter<View> {
        void attachLifecycleOwner(LifecycleOwner lifecycleOwner);
        void setFormData(String index);
        void saveData();
        void updateData();
    }
}
