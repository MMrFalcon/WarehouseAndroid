package com.falcon.warehouse.root;

import android.app.Application;

import com.falcon.warehouse.module.LocalisationAdapterModule;
import com.falcon.warehouse.module.LocalisationModule;
import com.falcon.warehouse.module.ProductAdapterModule;
import com.falcon.warehouse.module.ProductLocalisationAdapterModule;
import com.falcon.warehouse.module.ProductLocalisationModule;
import com.falcon.warehouse.module.ProductModule;

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
                .productModule(new ProductModule())
                .productLocalisationModule(new ProductLocalisationModule())
                .productLocalisationAdapterModule(new ProductLocalisationAdapterModule())
                .productAdapterModule(new ProductAdapterModule())
                .localisationAdapterModule(new LocalisationAdapterModule())
                .build();
    }

    public AppComponent getComponent() {
        return appComponent;
    }
}
