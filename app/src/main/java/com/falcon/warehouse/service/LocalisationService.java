package com.falcon.warehouse.service;

import com.falcon.warehouse.entity.Localisation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LocalisationService {

    @GET("localisation/index/{localisationIndex}")
    Call<Localisation> getLocalisationByIndex(@Path("localisationIndex") String localisationIndex);
}
