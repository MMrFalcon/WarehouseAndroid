package com.falcon.warehouse.module;

import com.falcon.warehouse.adapter.ProductLocalisationAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductLocalisationAdapterModule {

    @Singleton
    @Provides
    public ProductLocalisationAdapter provideAdapter() {
        return new ProductLocalisationAdapter();
    }

}
