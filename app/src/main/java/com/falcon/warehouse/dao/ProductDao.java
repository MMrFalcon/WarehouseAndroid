package com.falcon.warehouse.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.falcon.warehouse.entity.Product;

import java.util.Date;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProductDao {

    @Insert(onConflict = REPLACE)
    void saveProduct(Product product);

    @Query("SELECT * FROM product WHERE product_index LIKE :productIndex LIMIT 1")
    LiveData<Product> getProductByIndex(String productIndex);

    @Query("SELECT * FROM product WHERE product_index LIKE :productIndex AND last_fetched_date > :lastRefreshMax LIMIT 1")
    Product fetchedProduct(String productIndex, Date lastRefreshMax);

    @Query("SELECT * FROM product ORDER BY last_fetched_date LIMIT 1")
    LiveData<Product> getLastFetchedProduct();

    @Query("SELECT * FROM product ORDER BY product_id")
    LiveData<List<Product>> getAll();

}
