package com.falcon.warehouse.module;

import com.falcon.warehouse.contract.ILocalisationAddUpdateContract;
import com.falcon.warehouse.contract.ILocalisationDetailContract;
import com.falcon.warehouse.contract.ILocalisationListContract;
import com.falcon.warehouse.contract.ILocalisationScannerContract;
import com.falcon.warehouse.model.LocalisationAddEditModel;
import com.falcon.warehouse.model.LocalisationDetailModel;
import com.falcon.warehouse.model.LocalisationListModel;
import com.falcon.warehouse.model.LocalisationScannerModel;
import com.falcon.warehouse.presenter.LocalisationAddEditPresenter;
import com.falcon.warehouse.presenter.LocalisationDetailPresenter;
import com.falcon.warehouse.presenter.LocalisationListPresenter;
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

    @Singleton
    @Provides
    public ILocalisationListContract.Presenter provideLocalisationListPresenter(ILocalisationListContract.Model model) {
        return new LocalisationListPresenter(model);
    }

    @Singleton
    @Provides
    public  ILocalisationListContract.Model provideLocalisationListModel(LocalisationRepository localisationRepository) {
        return new LocalisationListModel(localisationRepository);
    }

    @Singleton
    @Provides
    public ILocalisationAddUpdateContract.Presenter provideAddUpdatePresenter(ILocalisationAddUpdateContract.Model model) {
        return new LocalisationAddEditPresenter(model);
    }

    @Singleton
    @Provides
    public ILocalisationAddUpdateContract.Model provideAddUpdateModel(LocalisationRepository localisationRepository) {
        return new LocalisationAddEditModel(localisationRepository);
    }
}
