package com.falcon.warehouse.repository;

import com.falcon.warehouse.dao.ProductLocalisationDao;
import com.falcon.warehouse.entity.ProductLocalisation;

public interface ProductLocalisationRepository extends ProductLocalisationDao, BaseRepository {
    void updateProductLocalisation(ProductLocalisation productLocalisation);
}
