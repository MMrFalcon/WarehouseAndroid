package com.falcon.warehouse.service;

import com.falcon.warehouse.entity.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductService {

    @GET("product/index/{productIndex}")
    Call<Product> getProductByIndex(@Path("productIndex") String productIndex);

    @GET("product")
    Call<List<Product>> geAllProducts();

    @POST("product")
    Call<Product> saveProduct(@Body Product product);

    @PUT("product")
    Call<Product> updateProduct(@Body Product product);

    @HTTP(method = "DELETE", path = "product", hasBody = true)
    Call<Void> delteProduct(@Body Product product);
}
