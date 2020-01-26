package com.falcon.warehouse.service;

import com.falcon.warehouse.entity.ProductLocalisation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

public interface ProductLocalisationService {

    @GET("productLocalisation/localisationIndex/{localisationIndex}")
    Call<List<ProductLocalisation>> getByLocalisationIndex(@Path("localisationIndex") String localisationIndex);

    @GET("productLocalisation/productIndex/{productIndex}")
    Call<List<ProductLocalisation>> getByProductIndex(@Path("productIndex") String productIndex);

    @GET("productLocalisation")
    Call<List<ProductLocalisation>> getAll();

    @HTTP(method = "DELETE", path = "productLocalisation", hasBody = true)
    Call<Void> delete(@Body ProductLocalisation productLocalisation);
}
