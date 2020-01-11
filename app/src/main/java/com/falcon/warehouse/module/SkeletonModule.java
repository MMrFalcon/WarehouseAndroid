package com.falcon.warehouse.module;

import com.falcon.warehouse.contract.IWalkingSkeletonContract;
import com.falcon.warehouse.model.SkeletonModel;
import com.falcon.warehouse.presenter.SkeletonPresenter;
import com.falcon.warehouse.repository.SkeletonRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class SkeletonModule {

    @Provides
    public IWalkingSkeletonContract.Presenter providePresenter(IWalkingSkeletonContract.Model model) {
        return new SkeletonPresenter(model);
    }

    @Provides
    public IWalkingSkeletonContract.Model provideModel(SkeletonRepository skeletonRepository) {
       return new SkeletonModel(skeletonRepository);
    }

}
