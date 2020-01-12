package com.falcon.warehouse.root;

import com.falcon.warehouse.LocalisationActivity;
import com.falcon.warehouse.MainActivity;
import com.falcon.warehouse.SkeletonActivity;
import com.falcon.warehouse.module.LocalisationModule;
import com.falcon.warehouse.module.SkeletonModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, SkeletonModule.class, WarehouseModule.class, RetrofitModule.class,
        LocalisationModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);
    void inject(SkeletonActivity skeletonActivity);
    void inject(LocalisationActivity localisationActivity);

}
