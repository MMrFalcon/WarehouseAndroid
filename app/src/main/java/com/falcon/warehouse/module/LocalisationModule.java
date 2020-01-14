package com.falcon.warehouse.module;

import com.falcon.warehouse.contract.ILocalisationDetailContract;
import com.falcon.warehouse.contract.ILocalisationScannerContract;
import com.falcon.warehouse.model.LocalisationDetailModel;
import com.falcon.warehouse.model.LocalisationScannerModel;
import com.falcon.warehouse.presenter.LocalisationDetailPresenter;
import com.falcon.warehouse.presenter.LocalisationScannerPresenter;
import com.falcon.warehouse.repository.LocalisationRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalisationModule {

    @Singleton
    @Provides
    public ILocalisationDetailContract.Presenter provideLocalisationPresenter(ILocalisationDetailContract.Model model) {
        return new LocalisationDetailPresenter(model);
    }

    @Singleton
    @Provides
    public ILocalisationDetailContract.Model provideLocalisationModel(LocalisationRepository localisationRepository) {
        return new LocalisationDetailModel(localisationRepository);
    }

    @Singleton
    @Provides
    public ILocalisationScannerContract.Presenter provideLocalisationScannerPresenter(ILocalisationScannerContract.Model model) {
        return new LocalisationScannerPresenter(model);
    }

    @Singleton
    @Provides
    public  ILocalisationScannerContract.Model provideLocalisationScannerModel(LocalisationRepository localisationRepository) {
        return new LocalisationScannerModel(localisationRepository);
    }
}
