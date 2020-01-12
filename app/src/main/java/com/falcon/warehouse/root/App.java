package com.falcon.warehouse.root;

import android.app.Application;

import com.falcon.warehouse.module.LocalisationModule;
import com.falcon.warehouse.module.SkeletonModule;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .warehouseModule(new WarehouseModule(this))
                .skeletonModule(new SkeletonModule())
                .retrofitModule(new RetrofitModule())
                .localisationModule(new LocalisationModule())
                .build();
    }

    public AppComponent getComponent() {
        return appComponent;
    }
}
