package com.falcon.warehouse.module;

import com.falcon.warehouse.adapter.LocalisationAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalisationAdapterModule {

    @Singleton
    @Provides
    public LocalisationAdapter provideAdapter() {
        return new LocalisationAdapter();
    }
}
