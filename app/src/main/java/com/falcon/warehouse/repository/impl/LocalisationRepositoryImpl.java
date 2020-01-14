package com.falcon.warehouse.repository.impl;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.dao.LocalisationDao;
import com.falcon.warehouse.entity.Localisation;
import com.falcon.warehouse.repository.LocalisationRepository;
import com.falcon.warehouse.service.LocalisationService;

import java.util.Date;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocalisationRepositoryImpl extends BaseRepositoryImpl implements LocalisationRepository {

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
        localisationDao.saveLocalisation(localisation);
    }

    @Override
    public LiveData<Localisation> getLocalisationByIndex(String localisationIndex) {
        if (localisationIndex.isEmpty()) {
            return localisationDao.getLastFetchedLocalisation();
        } else {
            fetchLocalisationByIndex(localisationIndex);
            return localisationDao.getLocalisationByIndex(localisationIndex);
        }
    }

    /**
     * Method checks if data was fetched. Is only for executor call
     *
     * @param localisationIndex index of localisation
     * @param lastRefreshMax Date of last refresh -3 minutes
     * @return localisation if exists or null
     */
    @Override
    public Localisation fetchedLocalisation(String localisationIndex, Date lastRefreshMax) {
        return localisationDao.fetchedLocalisation(localisationIndex, lastRefreshMax);
    }

    @Override
    public LiveData<Localisation> getLastFetchedLocalisation() {
        return localisationDao.getLastFetchedLocalisation();
    }

    private void fetchLocalisationByIndex(final String localisationIndex) {
        executor.execute(() ->{
            boolean isRefreshTime = (localisationDao
                    .fetchedLocalisation(localisationIndex, getMaxRefreshTime(new Date()))== null);

            if (isRefreshTime) {
                localisationService.getLocalisationByIndex(localisationIndex).enqueue(new Callback<Localisation>() {
                    @Override
                    public void onResponse(Call<Localisation> call, Response<Localisation> response) {
                        executor.execute(() -> {
                            Localisation localisation = response.body();
                            localisation.setLastFetchedDate(new Date());
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

}
