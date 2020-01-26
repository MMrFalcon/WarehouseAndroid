package com.falcon.warehouse.service;

import com.falcon.warehouse.entity.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductService {

    @GET("product/index/{productIndex}")
    Call<Product> getProductByIndex(@Path("productIndex") String productIndex);

    @GET("product")
    Call<List<Product>> geAllProducts();
}
