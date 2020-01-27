package com.falcon.warehouse.repository;

import com.falcon.warehouse.dao.ProductDao;

import java.math.BigDecimal;

public interface ProductRepository extends BaseRepository, ProductDao {
    void addLocalisationToProduct(String productIndex, String localisationIndex, BigDecimal quantity);
}
