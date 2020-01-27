package com.falcon.warehouse.model;

import com.falcon.warehouse.contract.IProductScannerContract;
import com.falcon.warehouse.repository.ProductRepository;

import java.math.BigDecimal;

public class ProductScannerModel implements IProductScannerContract.Model {

    private final ProductRepository productRepository;

    public ProductScannerModel(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void updateProductByIndex(String productIndex) {
        productRepository.getProductByIndex(productIndex);
    }

    @Override
    public void addLocalisationToProduct(String productIndex, String localisationIndex, BigDecimal quantity) {
        productRepository.addLocalisationToProduct(productIndex, localisationIndex, quantity);
    }
}
