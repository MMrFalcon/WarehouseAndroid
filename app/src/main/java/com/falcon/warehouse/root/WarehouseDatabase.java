package com.falcon.warehouse.root;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.falcon.warehouse.dao.LocalisationDao;
import com.falcon.warehouse.dao.ProductDao;
import com.falcon.warehouse.dao.ProductLocalisationDao;
import com.falcon.warehouse.entity.Localisation;
import com.falcon.warehouse.entity.Product;
import com.falcon.warehouse.entity.ProductLocalisation;

@Database(entities = {Localisation.class, Product.class, ProductLocalisation.class}, exportSchema = false, version = 5)
@TypeConverters(Converters.class)
public abstract class WarehouseDatabase extends RoomDatabase {

    public abstract LocalisationDao getLocalisationDao();
    public abstract ProductDao getProductDao();
    public abstract ProductLocalisationDao getProductLocalisationDao();

}
