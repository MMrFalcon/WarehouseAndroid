package com.falcon.warehouse.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

@Entity(tableName = "product_localisation")
public class ProductLocalisation {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_localisation_id")
    @SerializedName("id")
    @Expose
    private Long id;

    @ColumnInfo(name = "localisation_id")
    @SerializedName("localisationId")
    @Expose
    private Long localisationId;

    @ColumnInfo(name = "product_id")
    @SerializedName("productId")
    @Expose
    private Long productId;

    @ColumnInfo(name = "localisation_index")
    @SerializedName("localisationIndex")
    @Expose
    private String localisationIndex;

    @ColumnInfo(name = "product_index")
    @SerializedName("productIndex")
    @Expose
    private String productIndex;

    @ColumnInfo(name = "quantity_in_localisation")
    @SerializedName("quantityInLocalisation")
    @Expose
    private BigDecimal quantity;

    @ColumnInfo(name = "last_fetched_date")
    private Date lastFetchedDate;

    public ProductLocalisation(Long id, Long localisationId, Long productId, String localisationIndex,
                               String productIndex, BigDecimal quantity) {
        this.id = id;
        this.localisationId = localisationId;
        this.productId = productId;
        this.localisationIndex = localisationIndex;
        this.productIndex = productIndex;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLocalisationId() {
        return localisationId;
    }

    public void setLocalisationId(Long localisationId) {
        this.localisationId = localisationId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Date getLastFetchedDate() {
        return lastFetchedDate;
    }

    public void setLastFetchedDate(Date lastFetchedDate) {
        this.lastFetchedDate = lastFetchedDate;
    }

    public String getLocalisationIndex() {
        return localisationIndex;
    }

    public void setLocalisationIndex(String localisationIndex) {
        this.localisationIndex = localisationIndex;
    }

    public String getProductIndex() {
        return productIndex;
    }

    public void setProductIndex(String productIndex) {
        this.productIndex = productIndex;
    }
}
