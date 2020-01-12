package com.falcon.warehouse.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity(tableName = "localisation")
public class Localisation {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "localisation_id")
    @SerializedName("id")
    @Expose
    private Long id;

    @ColumnInfo(name = "localisation_index")
    @SerializedName("localisationIndex")
    @Expose
    private String localisationIndex;

    @ColumnInfo(name = "localisation_name")
    @SerializedName("localisationName")
    @Expose
    private String localisationName;

    @ColumnInfo(name = "last_fetched_date")
    private Date lastFetchedDate;

    public Localisation(Long id, String localisationIndex, String localisationName) {
        this.id = id;
        this.localisationIndex = localisationIndex;
        this.localisationName = localisationName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalisationIndex() {
        return localisationIndex;
    }

    public void setLocalisationIndex(String localisationIndex) {
        this.localisationIndex = localisationIndex;
    }

    public String getLocalisationName() {
        return localisationName;
    }

    public void setLocalisationName(String localisationName) {
        this.localisationName = localisationName;
    }

    public Date getLastFetchedDate() {
        return lastFetchedDate;
    }

    public void setLastFetchedDate(Date lastFetchedDate) {
        this.lastFetchedDate = lastFetchedDate;
    }
}
