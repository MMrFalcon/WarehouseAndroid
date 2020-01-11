package com.falcon.warehouse.root;

import android.app.Application;

import androidx.room.Room;

import com.falcon.warehouse.dao.SkeletonDao;
import com.falcon.warehouse.repository.SkeletonRepository;
import com.falcon.warehouse.repository.impl.SkeletonRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class WarehouseModule {

    private final String DATABASE_NAME = "warehouse";
    private WarehouseDatabase warehouseDatabase;

    public WarehouseModule(Application application) {
        this.warehouseDatabase = Room
                .databaseBuilder(application, WarehouseDatabase.class, DATABASE_NAME)
                //todo change this before production
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    public WarehouseDatabase provideDatabase() {
        return warehouseDatabase;
    }

    @Singleton
    @Provides
    public SkeletonDao provideSkeletonDao(WarehouseDatabase warehouseDatabase) {
        return warehouseDatabase.getSkeletonDao();
    }

    @Singleton
    @Provides
    public SkeletonRepository provideSkeletonRepo(SkeletonDao skeletonDao) {
        return new SkeletonRepositoryImpl(skeletonDao);
    }

}
