package com.falcon.warehouse.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.falcon.warehouse.entity.Skeleton;

@Dao
public interface SkeletonDao {

    @Insert
    void insertSkeleton(Skeleton skeleton);

    @Query("SELECT * FROM skeleton WHERE name LIKE :skeletonName LIMIT 1")
    LiveData<Skeleton> findSkeletonByName(String skeletonName);
}
