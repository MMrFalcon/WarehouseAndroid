package com.falcon.warehouse.repository;

import com.falcon.warehouse.dao.LocalisationDao;

import java.math.BigDecimal;

public interface LocalisationRepository extends LocalisationDao, BaseRepository {
    void addProductToLocalisation(String localisationIndex, String productIndex, BigDecimal quantity);
}
