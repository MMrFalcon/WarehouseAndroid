package com.falcon.warehouse.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.falcon.warehouse.entity.ProductLocalisation;

import java.util.Date;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProductLocalisationDao {

    @Insert(onConflict = REPLACE)
    void saveProductLocalisation(ProductLocalisation productLocalisation);

    @Query("SELECT * FROM product_localisation WHERE localisation_index LIKE :localisationIndex")
    LiveData<List<ProductLocalisation>> getByLocalisationIndex(String localisationIndex);

    @Query("SELECT * FROM product_localisation WHERE  product_index LIKE :productIndex")
    LiveData<List<ProductLocalisation>> getByProductIndex(String productIndex);

    @Query("SELECT * FROM product_localisation WHERE localisation_index LIKE :localisationIndex AND last_fetched_date > :lastRefreshMax LIMIT 2")
    List<ProductLocalisation> lastFetchedByLocalisationIndex(String localisationIndex, Date lastRefreshMax);

    @Query("SELECT * FROM product_localisation WHERE product_index LIKE :productIndex AND last_fetched_date > :lastRefreshMax LIMIT 2")
    List<ProductLocalisation> lastFetchedByProductIndex(String productIndex, Date lastRefreshMax);

    @Query("SELECT * FROM product_localisation ORDER BY last_fetched_date LIMIT 2")
    LiveData<List<ProductLocalisation>> getLastFetchedProductLocalisations();
}
