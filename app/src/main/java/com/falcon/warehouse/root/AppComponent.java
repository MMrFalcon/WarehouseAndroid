package com.falcon.warehouse.root;

import com.falcon.warehouse.MainActivity;
import com.falcon.warehouse.fragment.LocalisationDetailFragment;
import com.falcon.warehouse.fragment.LocalisationScannerFragment;
import com.falcon.warehouse.module.LocalisationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, WarehouseModule.class, RetrofitModule.class,
        LocalisationModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);
    void inject(LocalisationDetailFragment localisationDetailFragment);
    void inject(LocalisationScannerFragment localisationScannerFragment);

}
