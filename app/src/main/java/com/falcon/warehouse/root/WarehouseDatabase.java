package com.falcon.warehouse.root;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.falcon.warehouse.dao.SkeletonDao;
import com.falcon.warehouse.entity.Skeleton;

@Database(entities = Skeleton.class, exportSchema = false, version = 1)
public abstract class WarehouseDatabase extends RoomDatabase {

    public abstract SkeletonDao getSkeletonDao();
}
