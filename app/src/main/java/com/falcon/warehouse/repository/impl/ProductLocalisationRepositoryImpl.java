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
        executor.execute(() -> productLocalisationService.getByLocalisationIndex(localisationIndex).enqueue(new Callback<List<ProductLocalisation>>() {
            @Override
            public void onResponse(Call<List<ProductLocalisation>> call, Response<List<ProductLocalisation>> response) {
                executor.execute(() -> {
                    List<ProductLocalisation> productLocalisations = response.body();
                    if (productLocalisations != null) {
                        productLocalisations.forEach(productLocalisation -> {
                            productLocalisation.setLastFetchedDate(new Date());
                            productLocalisationDao.saveProductLocalisation(productLocalisation);
                        });

                    } else {
                        Log.i("NULL_OBJECT", "ProductLocalisation is null");
                    }
                });
            }

            @Override
            public void onFailure(Call<List<ProductLocalisation>> call, Throwable t) {
                Log.e("ERROR_IN_FETCH", call.toString() + t.getLocalizedMessage());
                Log.e("SKIPPING_DATA_UPDATE", "Fetching data locally");
            }
        }));
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
        executor.execute(() -> productLocalisationService.getByProductIndex(productIndex).enqueue(new Callback<List<ProductLocalisation>>() {
            @Override
            public void onResponse(Call<List<ProductLocalisation>> call, Response<List<ProductLocalisation>> response) {
                executor.execute(() -> {
                    List<ProductLocalisation> productLocalisations = response.body();

                    if (productLocalisations != null) {
                        productLocalisations.forEach(productLocalisation -> {
                            productLocalisation.setLastFetchedDate(new Date());
                            productLocalisationDao.saveProductLocalisation(productLocalisation);
                        });
                    } else {
                        Log.i("NULL_OBJECT", "ProductLocalisation is null");
                    }
                });
            }

            @Override
            public void onFailure(Call<List<ProductLocalisation>> call, Throwable t) {
                Log.e("ERROR_IN_FETCH", call.toString() + t.getLocalizedMessage());
                Log.e("SKIPPING_DATA_UPDATE", "Fetching data locally");
            }
        }));
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

    @Override
    public LiveData<List<ProductLocalisation>> getAll() {
        fetchAll();
        return productLocalisationDao.getAll();
    }

    @Override
    public void delete(ProductLocalisation productLocalisation) {
        try {
            apiDelete(productLocalisation);
        } catch (RuntimeException ex) {
            Log.e("Catching Response", ex.getLocalizedMessage());
            productLocalisationDao.delete(productLocalisation);
        }
    }

    @Override
    public void deleteAll() {
        productLocalisationDao.deleteAll();
    }

    @Override
    public void updateProductLocalisationLocally(ProductLocalisation productLocalisation) {
        productLocalisationDao.updateProductLocalisationLocally(productLocalisation);
    }

    private void apiDelete(ProductLocalisation productLocalisation) {
        executor.execute(() -> productLocalisationService.delete(productLocalisation).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                executor.execute(() -> productLocalisationDao.delete(productLocalisation));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("ERROR_IN_FETCH", call.toString() + t.getLocalizedMessage());
                Log.e("SKIPPING_DATA_UPDATE", "Fetching data locally");
                throw new RuntimeException("Error in delete response");
            }
        }));
    }

    private void fetchAll() {
        executor.execute(() -> productLocalisationService.getAll().enqueue(new Callback<List<ProductLocalisation>>() {
            @Override
            public void onResponse(Call<List<ProductLocalisation>> call, Response<List<ProductLocalisation>> response) {
                executor.execute(() -> {
                    List<ProductLocalisation> productLocalisations = response.body();

                    if (productLocalisations != null) {
                        //Cleaning local table
                        deleteAll();
                        productLocalisations.forEach(productLocalisation -> {
                            productLocalisation.setLastFetchedDate(new Date());
                            productLocalisationDao.saveProductLocalisation(productLocalisation);
                        });
                    } else {
                        Log.e("NULL_OBJECT", "ProductLocalisation is null");
                    }
                });
            }

            @Override
            public void onFailure(Call<List<ProductLocalisation>> call, Throwable t) {
                Log.e("ERROR_IN_FETCH", call.toString() + t.getLocalizedMessage());
                Log.e("SKIPPING_DATA_UPDATE", "Fetching data locally");
            }
        }));
    }

    @Override
    public void updateProductLocalisation(ProductLocalisation productLocalisation) {
        executor.execute(() -> productLocalisationService.updateLocalisation(productLocalisation).enqueue(new Callback<ProductLocalisation>() {
            @Override
            public void onResponse(Call<ProductLocalisation> call, Response<ProductLocalisation> response) {
                executor.execute(() -> {
                    ProductLocalisation updatedEntity = response.body();

                    //Update only if api update works
                    if (updatedEntity != null) {
                        updateProductLocalisationLocally(updatedEntity);
                    } else {
                        Log.e("NULL_OBJECT", "ProductLocalisation is null");
                    }
                });
            }

            @Override
            public void onFailure(Call<ProductLocalisation> call, Throwable t) {
                Log.e("ERROR_IN_FETCH", call.toString() + t.getLocalizedMessage());
                Log.e("SKIPPING_DATA_UPDATE", "Fetching data locally");
            }
        }));
    }
}
