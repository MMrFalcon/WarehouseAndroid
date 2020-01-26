package com.falcon.warehouse.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.falcon.warehouse.entity.Localisation;

import java.util.Date;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface LocalisationDao {

    @Insert(onConflict = REPLACE)
    void saveLocalisation(Localisation localisation);

    @Query("SELECT * FROM localisation WHERE localisation_index LIKE :localisationIndex LIMIT 1")
    LiveData<Localisation> getLocalisationByIndex(String localisationIndex);

    @Query("SELECT * FROM localisation WHERE localisation_index LIKE :localisationIndex AND last_fetched_date > :lastRefreshMax LIMIT 1")
    Localisation fetchedLocalisation(String localisationIndex, Date lastRefreshMax);

    @Query("SELECT * FROM localisation ORDER BY last_fetched_date LIMIT 1")
    LiveData<Localisation> getLastFetchedLocalisation();

    @Query("SELECT * FROM localisation ORDER BY localisation_id")
    LiveData<List<Localisation>> getAll();
}
