package com.falcon.warehouse.repository.impl;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.dao.LocalisationDao;
import com.falcon.warehouse.entity.Localisation;
import com.falcon.warehouse.repository.LocalisationRepository;
import com.falcon.warehouse.root.Constants;
import com.falcon.warehouse.service.LocalisationService;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocalisationRepositoryImpl implements LocalisationRepository {

    private final LocalisationDao localisationDao;
    private final LocalisationService localisationService;
    private final Executor executor;

    @Inject
    public LocalisationRepositoryImpl(LocalisationDao localisationDao, LocalisationService localisationService, Executor executor) {
        this.localisationDao = localisationDao;
        this.localisationService = localisationService;
        this.executor = executor;
    }

    @Override
    public void saveLocalisation(Localisation localisation) {
        Log.i("LOCALISATION_REPO", "SAVING LOCALISATION" + localisation.getLocalisationName());
        localisationDao.saveLocalisation(localisation);
    }

    @Override
    public LiveData<Localisation> getLocalisationByIndex(String localisationIndex) {
        Log.i("LOCALISATION_REPO", "GET LOCALISATION BY INDEX IN LOCALISATION REPO");
        fetchLocalisationByIndex(localisationIndex);
        return localisationDao.getLocalisationByIndex(localisationIndex);
    }

    //todo is in UI thread only for executor call
    @Override
    public Localisation fetchedLocalisation(String localisationIndex, Date lastRefreshMax) {
        return localisationDao.fetchedLocalisation(localisationIndex, lastRefreshMax);
    }

    private void fetchLocalisationByIndex(final String localisationIndex) {
        Log.i("FETCH_BY_INDEX", localisationIndex);
        executor.execute(() ->{
            boolean isRefreshTime = (localisationDao
                    .fetchedLocalisation(localisationIndex, getMaxRefreshTime(new Date()))== null);

            if (isRefreshTime) {
                Log.i("FETCHING_LOC", "FETCHING LOCALISATION FROM API");
                localisationService.getLocalisationByIndex(localisationIndex).enqueue(new Callback<Localisation>() {
                    @Override
                    public void onResponse(Call<Localisation> call, Response<Localisation> response) {
                        executor.execute(() -> {
                            Log.i("RESPONSE BODY", "" + response.body().getId() + response.body().getLocalisationName());
                            Localisation localisation = response.body();
                            localisation.setLastFetchedDate(new Date());
                            Log.i("LOCALISATION_F_sAVE", localisation.getLocalisationIndex());
                            localisationDao.saveLocalisation(localisation);
                        });
                    }

                    @Override
                    public void onFailure(Call<Localisation> call, Throwable t) {
                        Log.i("ERROR_IN_FETCH", call.toString() + t.getLocalizedMessage());
                        throw new RuntimeException(t);
                    }
                });
            }

        });
    }

    //todo add in base repository for all repositories
    private Date getMaxRefreshTime(Date currentDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MINUTE, -Constants.REFRESH_TIME_IN_MINUTES);
        return cal.getTime();
    }
}
