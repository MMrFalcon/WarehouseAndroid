package com.falcon.warehouse.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.falcon.warehouse.entity.Localisation;

import java.util.Date;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface LocalisationDao {

    @Insert(onConflict = REPLACE)
    void saveLocalisation(Localisation localisation);

    @Query("SELECT * FROM localisation WHERE localisation_index LIKE :localisationIndex LIMIT 1")
    LiveData<Localisation> getLocalisationByIndex(String localisationIndex);

    @Query("SELECT * FROM localisation WHERE localisation_index LIKE :localisationIndex AND last_fetched_date > :lastRefreshMax LIMIT 1")
    Localisation fetchedLocalisation(String localisationIndex, Date lastRefreshMax);
}
