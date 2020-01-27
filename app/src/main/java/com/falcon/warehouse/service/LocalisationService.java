package com.falcon.warehouse.service;

import com.falcon.warehouse.entity.Localisation;
import com.falcon.warehouse.entity.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LocalisationService {

    @GET("localisation/index/{localisationIndex}")
    Call<Localisation> getLocalisationByIndex(@Path("localisationIndex") String localisationIndex);

    @GET("localisation")
    Call<List<Localisation>> getAllLocalisations();

    @POST("localisation")
    Call<Localisation> saveLocalisation(@Body Localisation localisation);

    @PUT("localisation")
    Call<Localisation> updateLocalisation(@Body Localisation localisation);

    @HTTP(method = "DELETE", path = "localisation", hasBody = true)
    Call<Void> deleteLocalisation(@Body Localisation localisation);

    @PUT("localisation/add/product")
    Call<Localisation> addProductToLocalisation(@Query("localisationIndex") String localisationIndex, @Query("quantity") String quantity,
                                       @Body Product product);
}
