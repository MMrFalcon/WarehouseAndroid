package com.falcon.warehouse.root;

import com.falcon.warehouse.MainActivity;
import com.falcon.warehouse.fragment.LocalisationDetailFragment;
import com.falcon.warehouse.fragment.LocalisationFragment;
import com.falcon.warehouse.fragment.LocalisationScannerFragment;
import com.falcon.warehouse.fragment.ProductDetailFragment;
import com.falcon.warehouse.fragment.ProductFragment;
import com.falcon.warehouse.fragment.ProductLocalisationFragment;
import com.falcon.warehouse.fragment.ProductLocalisationScannerFragment;
import com.falcon.warehouse.fragment.ProductScannerFragment;
import com.falcon.warehouse.module.LocalisationModule;
import com.falcon.warehouse.module.ProductLocalisationModule;
import com.falcon.warehouse.module.ProductModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, WarehouseModule.class, RetrofitModule.class,
        LocalisationModule.class, ProductModule.class, ProductLocalisationModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);
    void inject(LocalisationFragment localisationFragment);
    void inject(LocalisationDetailFragment localisationDetailFragment);
    void inject(LocalisationScannerFragment localisationScannerFragment);
    void inject(ProductFragment productFragment);
    void inject(ProductScannerFragment productScannerFragment);
    void inject(ProductDetailFragment productDetailFragment);
    void inject(ProductLocalisationFragment productLocalisationFragment);
    void inject(ProductLocalisationScannerFragment productLocalisationScannerFragment);

}
