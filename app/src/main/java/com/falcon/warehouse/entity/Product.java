package com.falcon.warehouse.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

@Entity(tableName = "product")
public class Product {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    @SerializedName("id")
    @Expose
    private Long id;

    @ColumnInfo(name = "product_index")
    @SerializedName("productIndex")
    @Expose
    private String productIndex;

    @ColumnInfo(name = "product_name")
    @SerializedName("name")
    @Expose
    private String productName;

    @ColumnInfo(name = "quantity")
    @SerializedName("quantity")
    @Expose
    private BigDecimal quantity;

    @ColumnInfo(name = "last_fetched_date")
    private Date lastFetchedDate;

    public Product(Long id, String productIndex, String productName, BigDecimal quantity) {
        this.id = id;
        this.productIndex = productIndex;
        this.productName = productName;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductIndex() {
        return productIndex;
    }

    public void setProductIndex(String productIndex) {
        this.productIndex = productIndex;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
}
