package com.falcon.warehouse.service;

import com.falcon.warehouse.entity.ProductLocalisation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductLocalisationService {

    @GET("productLocalisation/localisationIndex/{localisationIndex}")
    Call<ProductLocalisation> getByLocalisationIndex(@Path("localisationIndex") String localisationIndex);

    @GET("productLocalisation/productIndex/{productIndex}")
    Call<ProductLocalisation> getByProductIndex(@Path("productIndex") String productIndex);
}
