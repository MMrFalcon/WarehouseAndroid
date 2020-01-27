package com.falcon.warehouse.root;

import android.app.Application;

import androidx.room.Room;

import com.falcon.warehouse.dao.LocalisationDao;
import com.falcon.warehouse.dao.ProductDao;
import com.falcon.warehouse.dao.ProductLocalisationDao;
import com.falcon.warehouse.repository.LocalisationRepository;
import com.falcon.warehouse.repository.ProductLocalisationRepository;
import com.falcon.warehouse.repository.ProductRepository;
import com.falcon.warehouse.repository.impl.LocalisationRepositoryImpl;
import com.falcon.warehouse.repository.impl.ProductLocalisationRepositoryImpl;
import com.falcon.warehouse.repository.impl.ProductRepositoryImpl;
import com.falcon.warehouse.service.LocalisationService;
import com.falcon.warehouse.service.ProductLocalisationService;
import com.falcon.warehouse.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class WarehouseModule {

    private final String DATABASE_NAME = "warehouse";
    private WarehouseDatabase warehouseDatabase;

    public WarehouseModule(Application application) {
        this.warehouseDatabase = Room
                .databaseBuilder(application, WarehouseDatabase.class, DATABASE_NAME)
                //todo change this before production
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    public WarehouseDatabase provideDatabase() {
        return warehouseDatabase;
    }

    @Singleton
    @Provides
    public Gson provideGson() {
        return new GsonBuilder().create();
    }


    @Singleton
    @Provides
    public Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Singleton
    @Provides
    public LocalisationDao provideLocalisationDao(WarehouseDatabase warehouseDatabase) {
        return warehouseDatabase.getLocalisationDao();
    }


    @Singleton
    @Provides
    public LocalisationService provideLocalisationApiService(Retrofit apiAdapter) {
        return apiAdapter.create(LocalisationService.class);
    }

    @Singleton
    @Provides
    public LocalisationRepository provideLocalisationRepo(LocalisationDao localisationDao,
                                                          LocalisationService localisationService,
                                                          Executor executor, ProductService productService) {
        return new LocalisationRepositoryImpl(localisationDao, localisationService, executor, productService);
    }

    @Singleton
    @Provides
    public ProductDao productDao(WarehouseDatabase warehouseDatabase) {
        return warehouseDatabase.getProductDao();
    }

    @Singleton
    @Provides
    public ProductService provideProductService(Retrofit apiAdapter) {
        return apiAdapter.create(ProductService.class);
    }

    @Singleton
    @Provides
    public ProductRepository provideProductRepo(ProductDao productDao, ProductService productService, Executor executor,
                                                LocalisationService localisationService) {
        return new ProductRepositoryImpl(productDao, productService, executor, localisationService);
    }

    @Singleton
    @Provides
    public ProductLocalisationDao productLocalisationDao(WarehouseDatabase warehouseDatabase) {
        return warehouseDatabase.getProductLocalisationDao();
    }

    @Singleton
    @Provides
    public ProductLocalisationService productLocalisationService(Retrofit apiAdapter) {
        return apiAdapter.create(ProductLocalisationService.class);
    }

    @Singleton
    @Provides
    public ProductLocalisationRepository productLocalisationRepository(ProductLocalisationDao productLocalisationDao,
                                                                       ProductLocalisationService productLocalisationService, Executor executor) {
        return new ProductLocalisationRepositoryImpl(productLocalisationDao, productLocalisationService, executor);
    }

}
