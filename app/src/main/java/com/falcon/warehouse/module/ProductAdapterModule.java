package com.falcon.warehouse.module;

import com.falcon.warehouse.adapter.ProductAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductAdapterModule {

    @Singleton
    @Provides
    public ProductAdapter provideAdapter() {
        return new ProductAdapter();
    }
}
