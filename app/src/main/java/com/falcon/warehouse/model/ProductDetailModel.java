package com.falcon.warehouse.model;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.IProductDetailContract;
import com.falcon.warehouse.entity.Product;
import com.falcon.warehouse.repository.ProductRepository;

public class ProductDetailModel implements IProductDetailContract.Model {

    private final ProductRepository productRepository;

    public ProductDetailModel(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public LiveData<Product> getProductByIndex(String productIndex) {
        return productRepository.getProductByIndex(productIndex);
    }
}
