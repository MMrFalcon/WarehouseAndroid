package com.falcon.warehouse.model;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.contract.IProductAddUpdateContract;
import com.falcon.warehouse.entity.Product;
import com.falcon.warehouse.repository.ProductRepository;

public class ProductAddEditModel implements IProductAddUpdateContract.Model {

    private final ProductRepository productRepository;

    public ProductAddEditModel(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(Product product) {
        productRepository.saveNewProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }

    @Override
    public LiveData<Product> getByIndex(String index) {
        return productRepository.getProductByIndex(index);
    }
}
