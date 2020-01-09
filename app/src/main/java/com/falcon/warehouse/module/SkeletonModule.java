package com.falcon.warehouse.module;

import com.falcon.warehouse.model.SkeletonModel;
import com.falcon.warehouse.presenter.SkeletonPresenter;
import com.falcon.warehouse.repository.SkeletonRepository;
import com.falcon.warehouse.repository.impl.SkeletonRepositoryImpl;
import com.falcon.warehouse.service.IWalkingSkeleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SkeletonModule {

    @Provides
    public IWalkingSkeleton.Presenter providePresenter(IWalkingSkeleton.Model model) {
        return new SkeletonPresenter(model);
    }

    @Provides
    public IWalkingSkeleton.Model provideModel(SkeletonRepository skeletonRepository) {
       return new SkeletonModel(skeletonRepository);
    }

    @Provides
    public SkeletonRepository provideRepository() {
        return new SkeletonRepositoryImpl();
    }
}
