package com.falcon.warehouse.repository.impl;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.dao.ProductLocalisationDao;
import com.falcon.warehouse.entity.ProductLocalisation;
import com.falcon.warehouse.repository.ProductLocalisationRepository;
import com.falcon.warehouse.service.ProductLocalisationService;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductLocalisationRepositoryImpl extends BaseRepositoryImpl implements ProductLocalisationRepository {

    private final ProductLocalisationDao productLocalisationDao;
    private final ProductLocalisationService productLocalisationService;
    private final Executor executor;

    @Inject
    public ProductLocalisationRepositoryImpl(ProductLocalisationDao productLocalisationDao,
                                             ProductLocalisationService productLocalisationService, Executor executor) {
        this.productLocalisationDao = productLocalisationDao;
        this.productLocalisationService = productLocalisationService;
        this.executor = executor;
    }

    @Override
    public void saveProductLocalisation(ProductLocalisation productLocalisation) {
        productLocalisationDao.saveProductLocalisation(productLocalisation);
    }

    @Override
    public LiveData<List<ProductLocalisation>> getByLocalisationIndex(String localisationIndex) {
        if (localisationIndex.isEmpty()) {
            return productLocalisationDao.getLastFetchedProductLocalisations();
        } else {
            fetchByLocalisationIndex(localisationIndex);
            return productLocalisationDao.getByLocalisationIndex(localisationIndex);
        }
    }

    private void fetchByLocalisationIndex(final String localisationIndex) {
        executor.execute(() -> {
            boolean isRefreshTime = (productLocalisationDao.lastFetchedByLocalisationIndex(localisationIndex,
                    getMaxRefreshTime(new Date())) == null);

            if (isRefreshTime) {
                productLocalisationService.getByLocalisationIndex(localisationIndex).enqueue(new Callback<ProductLocalisation>() {
                    @Override
                    public void onResponse(Call<ProductLocalisation> call, Response<ProductLocalisation> response) {
                        executor.execute(() -> {
                            ProductLocalisation productLocalisation = response.body();
                            if (productLocalisation != null) {
                                productLocalisation.setLastFetchedDate(new Date());
                                productLocalisationDao.saveProductLocalisation(productLocalisation);
                            } else {
                                Log.i("NULL_OBJECT", "ProductLocalisation is null");
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<ProductLocalisation> call, Throwable t) {
                        Log.i("ERROR_IN_FETCH", call.toString() + t.getLocalizedMessage());
                        Log.i("SKIPPING_DATA_UPDATE", "Fetching data locally");
                    }
                });
            }
        });
    }

    @Override
    public LiveData<List<ProductLocalisation>> getByProductIndex(String productIndex) {
        if (productIndex.isEmpty()) {
            return productLocalisationDao.getLastFetchedProductLocalisations();
        } else {
            fetchByProductIndex(productIndex);
            return productLocalisationDao.getByProductIndex(productIndex);
        }

    }

    private void fetchByProductIndex(final String productIndex) {
        executor.execute(() -> {
            boolean isRefreshTime = (productLocalisationDao.lastFetchedByProductIndex(productIndex,
                    getMaxRefreshTime(new Date())) == null);

            if (isRefreshTime) {
                productLocalisationService.getByProductIndex(productIndex).enqueue(new Callback<ProductLocalisation>() {
                    @Override
                    public void onResponse(Call<ProductLocalisation> call, Response<ProductLocalisation> response) {
                        executor.execute(() -> {
                            ProductLocalisation productLocalisation = response.body();
                            if (productLocalisation != null) {
                                productLocalisation.setLastFetchedDate(new Date());
                                productLocalisationDao.saveProductLocalisation(productLocalisation);
                            } else {
                                Log.i("NULL_OBJECT", "ProductLocalisation is null");
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<ProductLocalisation> call, Throwable t) {
                        Log.i("ERROR_IN_FETCH", call.toString() + t.getLocalizedMessage());
                        Log.i("SKIPPING_DATA_UPDATE", "Fetching data locally");
                    }
                });
            }
        });
    }

    @Override
    public List<ProductLocalisation> lastFetchedByLocalisationIndex(String localisationIndex, Date lastRefreshMax) {
        return productLocalisationDao.lastFetchedByLocalisationIndex(localisationIndex, lastRefreshMax);
    }

    @Override
    public List<ProductLocalisation> lastFetchedByProductIndex(String productIndex, Date lastRefreshMax) {
        return productLocalisationDao.lastFetchedByProductIndex(productIndex, lastRefreshMax);
    }

    @Override
    public LiveData<List<ProductLocalisation>> getLastFetchedProductLocalisations() {
        return productLocalisationDao.getLastFetchedProductLocalisations();
    }
}
