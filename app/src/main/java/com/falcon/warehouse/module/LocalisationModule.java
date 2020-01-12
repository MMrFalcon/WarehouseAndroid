package com.falcon.warehouse.module;

import com.falcon.warehouse.contract.ILocalisationContract;
import com.falcon.warehouse.model.LocalisationModel;
import com.falcon.warehouse.presenter.LocalisationPresenter;
import com.falcon.warehouse.repository.LocalisationRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalisationModule {

    @Singleton
    @Provides
    public ILocalisationContract.Presenter provideLocalisationPresenter(ILocalisationContract.Model model) {
        return new LocalisationPresenter(model);
    }

    @Singleton
    @Provides
    public ILocalisationContract.Model provideLocalisationModel(LocalisationRepository localisationRepository) {
        return new LocalisationModel(localisationRepository);
    }
}
