package com.falcon.warehouse.root;

import android.app.Application;

import com.falcon.warehouse.module.LocalisationModule;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .warehouseModule(new WarehouseModule(this))
                .retrofitModule(new RetrofitModule())
                .localisationModule(new LocalisationModule())
                .build();
    }

    public AppComponent getComponent() {
        return appComponent;
    }
}
