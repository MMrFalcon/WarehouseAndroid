package com.falcon.warehouse.root;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.falcon.warehouse.dao.LocalisationDao;
import com.falcon.warehouse.entity.Localisation;

@Database(entities = {Localisation.class}, exportSchema = false, version = 3)
@TypeConverters(Converters.class)
public abstract class WarehouseDatabase extends RoomDatabase {

    public abstract LocalisationDao getLocalisationDao();
}
