package com.falcon.warehouse.root;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.falcon.warehouse.dao.LocalisationDao;
import com.falcon.warehouse.dao.SkeletonDao;
import com.falcon.warehouse.entity.Localisation;
import com.falcon.warehouse.entity.Skeleton;

@Database(entities = {Skeleton.class, Localisation.class}, exportSchema = false, version = 2)
@TypeConverters(Converters.class)
public abstract class WarehouseDatabase extends RoomDatabase {

    public abstract SkeletonDao getSkeletonDao();
    public abstract LocalisationDao getLocalisationDao();
}
