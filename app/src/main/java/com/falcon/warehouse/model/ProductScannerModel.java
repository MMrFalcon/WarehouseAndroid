package com.falcon.warehouse.model;

import com.falcon.warehouse.contract.IProductScannerContract;
import com.falcon.warehouse.repository.ProductRepository;

public class ProductScannerModel implements IProductScannerContract.Model {

    private final ProductRepository productRepository;

    public ProductScannerModel(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void updateProductByIndex(String productIndex) {
        productRepository.getProductByIndex(productIndex);
    }
}
