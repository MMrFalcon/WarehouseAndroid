package com.falcon.warehouse.model;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.IProductListContract;
import com.falcon.warehouse.entity.Product;
import com.falcon.warehouse.repository.ProductRepository;

import java.util.List;

public class ProductListModel implements IProductListContract.Model {

    private final ProductRepository productRepository;

    public ProductListModel(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public LiveData<List<Product>> getAll() {
        return productRepository.getAll();
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.deleteProduct(product);
    }
}
